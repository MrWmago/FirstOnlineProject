package com.example.xw.firstonlineproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xw on 2016/11/23.
 */

public class UserResult {
    private int code;
    @SerializedName("msg")
    private String message;
    private User data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public User getData() {
        return data;
    }
}
