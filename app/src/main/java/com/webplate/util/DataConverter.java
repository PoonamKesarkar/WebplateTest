package com.webplate.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webplate.model.Address;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

class DataConverter implements Serializable {
    private static Gson gson = new Gson();
    @TypeConverter
    public static List<Address> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Address>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListToString(List<Address> someObjects) {
        return gson.toJson(someObjects);
    }
}
