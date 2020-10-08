package com.example.i_partner_binet_task_job.pojo.responses;

import com.google.gson.annotations.SerializedName;

public class JDataEntry {
    @SerializedName("id")
    private String id;
    @SerializedName("body")
    private String body;
    @SerializedName("da")
    private String created;
    @SerializedName("dm")
    private String edited;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }
}
