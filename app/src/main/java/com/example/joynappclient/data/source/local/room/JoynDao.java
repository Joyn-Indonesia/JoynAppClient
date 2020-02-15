package com.example.joynappclient.data.source.local.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.joynappclient.data.source.local.entity.LocalUserLogin;

@Dao
public interface JoynDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLoginUser(LocalUserLogin user);

    @Query("select * from tb_user_login")
    LiveData<LocalUserLogin> getUserLogin();

    @Update
    void updateUserLogin(LocalUserLogin user);

    @Query("Delete from tb_user_login")
    void deleteUserLogin();
}
