package com.webplate.database;

import com.webplate.model.UserData;

import java.util.ArrayList;
import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoAccess {

    @Query("SELECT * FROM UserTable")
    List<UserData> getAllData();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<UserData> userList);

    @Query("SELECT * FROM UserTable")
    DataSource.Factory<Integer, UserData> concertsByDate();
}
