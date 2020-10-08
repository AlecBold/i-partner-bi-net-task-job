package com.example.i_partner_binet_task_job.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.i_partner_binet_task_job.di.scopes.FragmentScope;
import com.example.i_partner_binet_task_job.repository.SessionRepository;

import javax.inject.Inject;


@FragmentScope
public class SessionViewModel extends ViewModel {

    private SessionRepository repository;

    @Inject
    public SessionViewModel(SessionRepository repository) {
        this.repository = repository;
    }

    public LiveData<String> getSession() {
        return repository.getSession();
    }

}
