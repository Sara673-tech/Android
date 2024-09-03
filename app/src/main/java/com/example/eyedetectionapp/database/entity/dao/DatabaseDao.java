package com.example.eyedetectionapp.database.entity.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eyedetectionapp.database.entity.UserEntity;

@Dao
public interface DatabaseDao {

    @Insert
    void insertUser(UserEntity userEntity);

    @Query("SELECT COUNT(*) FROM UserEntity WHERE email=:Email AND password=:Pass")
    int userLogin(String Email, String Pass);

}
