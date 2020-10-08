package com.example.i_partner_binet_task_job.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.i_partner_binet_task_job.utils.Variables;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {


    @Singleton
    @Provides
    public SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences(Variables.KEY_PREFS, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public SharedPreferences.Editor provideEditor(Context context) {
        return context.getSharedPreferences(Variables.KEY_PREFS, Context.MODE_PRIVATE).edit();
    }
}
