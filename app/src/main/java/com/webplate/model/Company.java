package com.webplate.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;

public class Company implements Serializable {
    @SerializedName("name")
    @ColumnInfo(name = "companyname")
    public String name;
    @SerializedName("catchPhrase")
    @ColumnInfo(name = "catchPhrase")
    public String catchPhrase;
    @SerializedName("bs")
    @ColumnInfo(name = "bs")
    public String bs;
}
