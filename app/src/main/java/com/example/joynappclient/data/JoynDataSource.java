package com.example.joynappclient.data;

import com.google.firebase.firestore.Query;

public interface JoynDataSource {

    Query checkPhoneNumber(String number, String reference);

}
