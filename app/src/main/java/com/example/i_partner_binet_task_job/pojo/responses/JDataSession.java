package com.example.i_partner_binet_task_job.pojo.responses;

import com.google.gson.annotations.SerializedName;

public class JDataSession {
    @SerializedName("session")
    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
