package com.yascode.pemadamkebakaran;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caksono21 on 02/12/17.
 */

public class Pemadam  {
    String status;
    int count;

    //    @SerializedName("data")
    List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
