package com.example.i_partner_binet_task_job.di.component.sub;

import com.example.i_partner_binet_task_job.di.scopes.ActivityScope;
import com.example.i_partner_binet_task_job.ui.fragments.EntryFragment;
import com.example.i_partner_binet_task_job.ui.fragments.ListEntryFragment;
import com.example.i_partner_binet_task_job.ui.fragments.NewEntryFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface EntrySubComponent {

    @Subcomponent.Factory
    interface Factory {
        EntrySubComponent create();
    }

    void inject(EntryFragment entryFragment);
    void inject(NewEntryFragment newEntryFragment);
    void inject(ListEntryFragment listEntryFragment);
}
