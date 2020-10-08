package com.example.i_partner_binet_task_job.di.component;

import android.content.Context;

import com.example.i_partner_binet_task_job.di.component.sub.EntrySubComponent;
import com.example.i_partner_binet_task_job.di.component.sub.LoadSubComponent;
import com.example.i_partner_binet_task_job.di.module.DataModule;
import com.example.i_partner_binet_task_job.di.module.NetworkModule;
import com.example.i_partner_binet_task_job.di.module.SubComponentsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, DataModule.class, SubComponentsModule.class})
public interface AppComponent {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }

    EntrySubComponent.Factory entrySubComponent();
    LoadSubComponent.Factory loadSubComponent();
}
