package com.example.i_partner_binet_task_job;

import android.app.Application;

import com.example.i_partner_binet_task_job.di.component.AppComponent;
import com.example.i_partner_binet_task_job.di.component.DaggerAppComponent;

public class MyApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(getApplicationContext());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
