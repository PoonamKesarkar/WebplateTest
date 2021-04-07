package com.webplate.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "UserTable")
public class UserData implements Serializable {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    public String name;

    @SerializedName("username")
    @ColumnInfo(name = "username")
    public String username;

    @SerializedName("email")
    @ColumnInfo(name = "email")
    public String email;

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    public String phone;

    @SerializedName("website")
    @ColumnInfo(name = "website")
    public String website;

    @Embedded
    @SerializedName("address")
    public Address address;

    @Embedded
    @SerializedName("company")
    public Company company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
