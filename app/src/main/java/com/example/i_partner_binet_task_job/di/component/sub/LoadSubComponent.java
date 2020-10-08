package com.example.i_partner_binet_task_job.di.component.sub;

import com.example.i_partner_binet_task_job.di.scopes.FragmentScope;
import com.example.i_partner_binet_task_job.ui.fragments.LoadFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent
public interface LoadSubComponent {

    @Subcomponent.Factory
    interface Factory {
        LoadSubComponent create();
    }

    void inject(LoadFragment loadFragment);
}
