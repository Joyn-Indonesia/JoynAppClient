package com.example.joynappclient.data;

import com.example.joynappclient.data.source.remote.FirebaseRepository;
import com.google.firebase.firestore.Query;

public class JoynRepository implements JoynDataSource {
    private static volatile JoynRepository INSTANCE = null;

    private FirebaseRepository firebaseRepository;

    public JoynRepository(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    public static JoynRepository getInstance(FirebaseRepository firebaseRepository) {
        if (INSTANCE == null) {
            synchronized (JoynRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JoynRepository(firebaseRepository);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public Query checkPhoneNumber(String number, String reference) {
        return firebaseRepository.checkPhoneNumber(number, reference);
    }
}
