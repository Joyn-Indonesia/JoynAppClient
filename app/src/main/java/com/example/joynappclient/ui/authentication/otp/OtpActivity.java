package com.example.joynappclient.ui.authentication.otp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.UserModel;
import com.example.joynappclient.utils.Constant;
import com.goodiebag.pinview.Pinview;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtpActivity extends AppCompatActivity {
    private static final String TAG = "OtpActivity";

    //widget
    @BindView(R.id.tv_numberPhone)
    TextView numberText;
    @BindView(R.id.pinview)
    Pinview pinCode;
    @BindView(R.id.btn_next)
    Button next;

    //vars
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;
    private PhoneAuthProvider.ForceResendingToken mResendingToken;
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private UserModel user;


    @OnClick(R.id.btn_changeNumber)
    public void changeNumber() {
        finish();
    }

    @OnClick(R.id.btn_next)
    public void redirectHomeMenu() {
        // MoveActivity.MoveAct(context, WelcomeToAppActivity.class);
        Toast.makeText(context, "nmax", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        context = this;
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();

        user = getIntent().getParcelableExtra(Constant.User);
        numberText.setText(user.getPhoneNumber());

        requestOtp(user.getPhoneNumber());
        widgetListener();

    }

    private void requestOtp(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                callbacks());
        mVerificationInProgress = true;
    }

    private void widgetListener() {
        pinCode.setPinViewEventListener((pinview, fromUser)
                -> {
            Toast.makeText(context, pinview.getValue(), Toast.LENGTH_SHORT).show();
            next.setEnabled(true);
            sendOtpCode(pinview.getValue());

        });


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks() {
        return new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e(TAG, "onVerificationFailed: ", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.e(TAG, "onVerificationFailed: invalid request");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Log.e(TAG, "onVerificationFailed: The SMS quota for the project has been exceeded");
                }
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                Log.e(TAG, "onCodeAutoRetrievalTimeOut: " + s);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Log.d(TAG, "onCodeSent: " + s);
                mVerificationId = s;
                mResendingToken = forceResendingToken;

            }
        };
    }

    private void sendOtpCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "onComplete: success");
                user.setUserId(mAuth.getUid());
                insertNewUser();
                //   FirebaseAuth.getInstance().signOut();
                Toast.makeText(OtpActivity.this, "OTP Sukses", Toast.LENGTH_SHORT).show();
            } else {
                Log.w(TAG, "signInWithCredential:failure", task.getException());
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                    Log.e(TAG, "Invalid code: ");
                }
            }
        });
    }

    private void insertNewUser() {
        DocumentReference newUserRef = mDb
                .collection(getString(R.string.collection_users))
                .document(user.getUserId());

        newUserRef.set(user).addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                redirectHomeMenu();
            } else {
                Toast.makeText(context, "Somethink Wrong", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void resendCodeVerify(String phone) {
        if (mResendingToken != null) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phone, 120, TimeUnit.SECONDS, this, callbacks(), mResendingToken
            );
            Toast.makeText(OtpActivity.this, "Kode dikirim ulang", Toast.LENGTH_SHORT).show();
        }
    }


}
