package com.example.i_partner_binet_task_job.pojo.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JResponse <D> {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private D data;
    @SerializedName("error")
    private String error;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
