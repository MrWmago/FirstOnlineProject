package com.example.xw.firstonlineproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xw on 2016/11/23.
 */

public class User {
    @SerializedName("name")
    private String hx_ID;
    @SerializedName("uuid")
    private String table_ID;
    @SerializedName("username")
    private String name;
    @SerializedName("other")
    private String head_Image;
    @SerializedName("nickname")
    private String nick_name;
    private String password;

    public String getHx_ID() {
        return hx_ID;
    }

    public void setHx_ID(String hx_ID) {
        this.hx_ID = hx_ID;
    }

    public String getTable_ID() {
        return table_ID;
    }

    public void setTable_ID(String table_ID) {
        this.table_ID = table_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_Image() {
        return head_Image;
    }

    public void setHead_Image(String head_Image) {
        this.head_Image = head_Image;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
