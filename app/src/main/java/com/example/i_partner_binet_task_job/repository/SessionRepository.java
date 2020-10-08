package com.example.i_partner_binet_task_job.repository;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.example.i_partner_binet_task_job.network.BNetApi;
import com.example.i_partner_binet_task_job.pojo.responses.JDataSession;
import com.example.i_partner_binet_task_job.pojo.responses.JResponse;
import com.example.i_partner_binet_task_job.utils.Variables;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class SessionRepository {
    private static final String TAG = "SessionRepository";

    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;

    @Inject
    public SessionRepository(SharedPreferences sharedPreferences, Retrofit retrofit) {
        this.sharedPreferences = sharedPreferences;
        this.retrofit = retrofit;
    }

    public MutableLiveData<String> getSession() {
        final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        String session = sharedPreferences.getString(Variables.KEY_SESSION,"");
        if (!TextUtils.isEmpty(session)) {
            mutableLiveData.setValue(session);
        } else {
            retrofit.create(BNetApi.class).newSession(Variables.METHOD_NEW_SESSION).enqueue(new Callback<JResponse<JDataSession>>() {
                @Override
                public void onResponse(Call<JResponse<JDataSession>> call, Response<JResponse<JDataSession>> response) {
                    if (response.isSuccessful()) {
                        String session = response.body().getData().getSession();
                        mutableLiveData.setValue(session);
                        saveSession(session);
                    }
                }

                @Override
                public void onFailure(Call<JResponse<JDataSession>> call, Throwable t) {
                    mutableLiveData.setValue(null);
                }
            });
        }
        return mutableLiveData;
    }

    protected void saveSession(String session) {
        sharedPreferences.edit().putString(Variables.KEY_SESSION, session).apply();
    }
}
