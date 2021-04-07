package com.webplate.database;

import com.webplate.model.Address;
import com.webplate.model.Company;
import com.webplate.model.UserData;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoAccess userDao();
}
