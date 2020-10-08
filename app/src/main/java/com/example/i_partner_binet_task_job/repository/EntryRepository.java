package com.example.i_partner_binet_task_job.repository;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.i_partner_binet_task_job.network.BNetApi;
import com.example.i_partner_binet_task_job.pojo.responses.JDataEntry;
import com.example.i_partner_binet_task_job.pojo.responses.JResponse;
import com.example.i_partner_binet_task_job.utils.Variables;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class EntryRepository {
    private static final String TAG = "EntryRepository";

    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;

    @Inject
    public EntryRepository(Retrofit retrofit, SharedPreferences sharedPreferences) {
        this.retrofit = retrofit;
        this.sharedPreferences = sharedPreferences;
    }

    public MutableLiveData<JResponse<List<List<JDataEntry>>>> getEntries() {
        final MutableLiveData<JResponse<List<List<JDataEntry>>>> mutableLiveData = new MutableLiveData<>();
        retrofit.create(BNetApi.class).getEntries(Variables.METHOD_GET_ENTRIES, getSession()).enqueue(new Callback<JResponse<List<List<JDataEntry>>>>() {
            @Override
            public void onResponse(Call<JResponse<List<List<JDataEntry>>>> call, Response<JResponse<List<List<JDataEntry>>>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<JResponse<List<List<JDataEntry>>>> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<JResponse<JDataEntry>> addEntry(String body) {
        final MutableLiveData<JResponse<JDataEntry>> mutableLiveData = new MutableLiveData<>();
        retrofit.create(BNetApi.class).addEntry(Variables.METHOD_ADD_ENTRY, getSession(), body).enqueue(new Callback<JResponse<JDataEntry>>() {
            @Override
            public void onResponse(Call<JResponse<JDataEntry>> call, Response<JResponse<JDataEntry>> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<JResponse<JDataEntry>> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }

    public String getSession() {
        return sharedPreferences.getString(Variables.KEY_SESSION, "");
    }
}
