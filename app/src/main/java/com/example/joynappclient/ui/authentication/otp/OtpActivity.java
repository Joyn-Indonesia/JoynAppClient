package com.example.joynappclient.ui.authentication.otp;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.joynappclient.R;
import com.example.joynappclient.data.source.UserModel;
import com.example.joynappclient.ui.authentication.welcome_to_app.WelcomeToAppActivity;
import com.example.joynappclient.utils.MoveActivity;
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
    @BindView(R.id.btn_resendCode)
    Button resendCode;

    //vars
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;
    private PhoneAuthProvider.ForceResendingToken mResendingToken;
    private String mVerificationId;
    private UserModel user;
    private boolean registerNewUser = true;
    private String phoneNumber;

    //onCLick
    @OnClick(R.id.btn_changeNumber)
    public void changeNumber() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        context = this;
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();

        if (getIntent() != null) {
            user = getIntent().getParcelableExtra(getString(R.string.intent_phone));
            phoneNumber = user.getPhoneNumber();
            if (user.getName() == null) {
                registerNewUser = false;
            }
            numberText.setText(phoneNumber);
            countDownTimer();
            requestOtp();
            widgetListener();
        }
    }

    private void countDownTimer() {
        resendCode.setEnabled(false);
        new CountDownTimer(60 * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                resendCode.setText("resend in " + millisUntilFinished / 1000 + " second");
            }

            public void onFinish() {
                resendCode.setText("Resend!");
                resendCode.setEnabled(true);
            }
        }.start();
    }

    private void requestOtp() {
        Log.d(TAG, "requestOtp: start request");
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                callbacks());
    }

    private void widgetListener() {

        pinCode.setPinViewEventListener((pinview, fromUser)
                -> {
            Toast.makeText(context, pinview.getValue(), Toast.LENGTH_SHORT).show();
            next.setEnabled(true);
            sendOtpCode(pinview.getValue());
            Log.d(TAG, "widgetListener: send pin");

        });

        resendCode.setOnClickListener(v -> {
            resendCodeVerify();
        });

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks() {
        return new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                signInWithPhoneAuthCredential(phoneAuthCredential);
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

    //  @OnClick(R.id.btn_next)
    public void sendOtpCode(String code) {
        Log.d(TAG, "sendOtpCode: start otp");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "onComplete: success");
                if (registerNewUser) {
                    user.setUserId(mAuth.getUid());
                    insertNewUser();
                    Log.d(TAG, "sendOtpCode: new user");
                } else {
                    redirectHomeMenu();
                    Log.d(TAG, "sendOtpCode: login user registered");
                }
            } else {
                Log.w(TAG, "signInWithCredential:failure", task.getException());
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                    Log.e(TAG, "Invalid code: ");
                    Toast.makeText(context, "Invalid Code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "onComplete: success");
                if (registerNewUser) {
                    user.setUserId(mAuth.getUid());
                    insertNewUser();
                    Log.d(TAG, "sendOtpCode: new user");
                } else {
                    redirectHomeMenu();
                    Log.d(TAG, "sendOtpCode: login user registered");
                }
            } else {
                Log.w(TAG, "signInWithCredential:failure", task.getException());
                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                    Log.e(TAG, "Invalid code: ");
                    Toast.makeText(context, "Invalid Code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertNewUser() {
        DocumentReference newUserRef = mDb
                .collection(getString(R.string.collection_users))
                .document(user.getUserId());

        newUserRef.set(user).addOnSuccessListener(this, aVoid -> {
            Log.d(TAG, "DocumentSnapshot successfully written!");
            redirectHomeMenu();
        }).addOnFailureListener(this, e -> Toast.makeText(context, "Somethink Wrong", Toast.LENGTH_SHORT).show());

    }

    public void resendCodeVerify() {
        Log.d(TAG, "resendCodeVerify: ");
        countDownTimer();
        if (mResendingToken != null) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber, 120, TimeUnit.SECONDS, this, callbacks(), mResendingToken
            );
            Toast.makeText(OtpActivity.this, "Kode dikirim ulang", Toast.LENGTH_SHORT).show();
        }
    }

    public void redirectHomeMenu() {
        MoveActivity.MoveAct(context, WelcomeToAppActivity.class);
    }
}
