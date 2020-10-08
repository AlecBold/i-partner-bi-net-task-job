package com.example.i_partner_binet_task_job.di.module;

import com.example.i_partner_binet_task_job.di.component.sub.EntrySubComponent;
import com.example.i_partner_binet_task_job.di.component.sub.LoadSubComponent;

import dagger.Module;

@Module(subcomponents = {EntrySubComponent.class, LoadSubComponent.class})
public class SubComponentsModule {
}
