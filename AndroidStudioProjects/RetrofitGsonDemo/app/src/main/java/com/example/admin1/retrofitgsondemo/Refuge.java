package com.example.admin1.retrofitgsondemo;

import java.util.List;

/**
 * Created by admin1 on 12/10/16.
 */

public class Refuge {

    private int code;
    private String success;
    private String message;
    private List<RefugeData> data;

    public void setCode(int code){
        this.code = code;
    }

    public void setData(List<RefugeData> data) {
        this.data = data;
    }

    public List<RefugeData> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
