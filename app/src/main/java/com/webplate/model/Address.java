package com.webplate.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.Embedded;

public class Address implements Serializable {
    @SerializedName("street")
    public String street;
    @SerializedName("suite")
    public String suite;
    @SerializedName("city")
    public String city;
    @SerializedName("zipcode")
    public String zipcode;
    @SerializedName("geo")
    @Embedded
    public Geo geo;
}
