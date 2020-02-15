package com.example.joynappclient.data.source.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseUserLogin implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nama_depan")
    @Expose
    private String namaDepan;

    @SerializedName("nama_belakang")
    @Expose
    private String namaBelakang;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("no_telepon")
    @Expose
    private String noTelepon;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("created_on")
    @Expose
    private String createdOn;

    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;

    @SerializedName("tempat_lahir")
    @Expose
    private String tempatLahir;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("reg_id")
    @Expose
    private String regId;

    @SerializedName("status")
    @Expose
    private String status;

    private long mPaySaldo;

    public ResponseUserLogin() {
    }

    protected ResponseUserLogin(Parcel in) {
        id = in.readString();
        namaDepan = in.readString();
        namaBelakang = in.readString();
        email = in.readString();
        noTelepon = in.readString();
        password = in.readString();
        alamat = in.readString();
        createdOn = in.readString();
        tglLahir = in.readString();
        tempatLahir = in.readString();
        rating = in.readString();
        regId = in.readString();
        status = in.readString();
        mPaySaldo = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(namaDepan);
        dest.writeString(namaBelakang);
        dest.writeString(email);
        dest.writeString(noTelepon);
        dest.writeString(password);
        dest.writeString(alamat);
        dest.writeString(createdOn);
        dest.writeString(tglLahir);
        dest.writeString(tempatLahir);
        dest.writeString(rating);
        dest.writeString(regId);
        dest.writeString(status);
        dest.writeLong(mPaySaldo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResponseUserLogin> CREATOR = new Creator<ResponseUserLogin>() {
        @Override
        public ResponseUserLogin createFromParcel(Parcel in) {
            return new ResponseUserLogin(in);
        }

        @Override
        public ResponseUserLogin[] newArray(int size) {
            return new ResponseUserLogin[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getmPaySaldo() {
        return mPaySaldo;
    }

    public void setmPaySaldo(long mPaySaldo) {
        this.mPaySaldo = mPaySaldo;
    }
}
