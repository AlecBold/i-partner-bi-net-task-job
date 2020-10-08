package com.example.i_partner_binet_task_job.network;

import com.example.i_partner_binet_task_job.pojo.responses.JDataEntry;
import com.example.i_partner_binet_task_job.pojo.responses.JDataSession;
import com.example.i_partner_binet_task_job.pojo.responses.JResponse;
import com.example.i_partner_binet_task_job.utils.Variables;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BNetApi {

    @Headers(Variables.HEADER_TOKEN)
    @FormUrlEncoded
    @POST("testAPI/")
    Call<JResponse<JDataSession>> newSession(
            @Field("a") String method);

    @Headers(Variables.HEADER_TOKEN)
    @FormUrlEncoded
    @POST("testAPI/")
    Call<JResponse<List<List<JDataEntry>>>> getEntries(
            @Field("a") String method,
            @Field("session") String session);

    @Headers(Variables.HEADER_TOKEN)
    @FormUrlEncoded
    @POST("testAPI/")
    Call<JResponse<JDataEntry>> addEntry(
            @Field("a") String method,
            @Field("session") String session,
            @Field("body") String data);

}
