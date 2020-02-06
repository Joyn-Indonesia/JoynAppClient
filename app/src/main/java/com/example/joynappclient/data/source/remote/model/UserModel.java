package com.example.joynappclient.data.source.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

public class UserModel implements Parcelable {

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
    private String name;
    private String eMail;
    private String phoneNumber;
    @PrimaryKey
    @NonNull
    private String userId;

    public UserModel() {
    }

    private String regId;

    public UserModel(String name, String eMail, String phoneNumber, String userId, String regId) {
        this.name = name;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.regId = regId;
    }

    protected UserModel(Parcel in) {
        name = in.readString();
        eMail = in.readString();
        phoneNumber = in.readString();
        userId = in.readString();
        regId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(eMail);
        dest.writeString(phoneNumber);
        dest.writeString(userId);
        dest.writeString(regId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }
}
