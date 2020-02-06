package com.example.joynappclient.data.source.local.room;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.joynappclient.data.source.local.entity.UserLogin;

@Dao
public interface JoynDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLoginUser(UserLogin user);

    @Query("select * from tb_user_login")
    LiveData<UserLogin> getUserLogin();

    @Update
    void updateUserLogin(UserLogin user);

    @Query("Delete from tb_user_login")
    void deleteUserLogin();
}
