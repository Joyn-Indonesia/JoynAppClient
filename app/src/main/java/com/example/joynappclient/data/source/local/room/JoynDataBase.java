package com.example.joynappclient.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.joynappclient.data.source.local.entity.UserLogin;

@Database(entities = {UserLogin.class},
        version = 1,
        exportSchema = false)
public abstract class JoynDataBase extends RoomDatabase {

    private static volatile JoynDataBase INSTANCE;

    public static JoynDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (JoynDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            JoynDataBase.class, "Joyn.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract JoynDao joynDao();


}
