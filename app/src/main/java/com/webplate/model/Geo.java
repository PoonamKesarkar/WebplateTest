package com.webplate.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Geo implements Serializable {
    @SerializedName("lat")
    public String lat;

    @SerializedName("lng")
    public String lng;
}
