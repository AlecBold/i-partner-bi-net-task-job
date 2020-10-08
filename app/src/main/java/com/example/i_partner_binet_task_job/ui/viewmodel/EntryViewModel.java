package com.example.i_partner_binet_task_job.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.i_partner_binet_task_job.di.scopes.ActivityScope;
import com.example.i_partner_binet_task_job.pojo.responses.JDataEntry;
import com.example.i_partner_binet_task_job.pojo.responses.JResponse;
import com.example.i_partner_binet_task_job.repository.EntryRepository;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class EntryViewModel extends ViewModel {
    private MutableLiveData<JResponse<List<List<JDataEntry>>>> mutableLiveData;
    private EntryRepository repository;

    @Inject
    public EntryViewModel(EntryRepository repository) {
        this.repository = repository;
    }

    public LiveData<JResponse<List<List<JDataEntry>>>> getEntries() {
        if (mutableLiveData == null) {
            mutableLiveData = repository.getEntries();
        }
        return mutableLiveData;
    }

    public LiveData<JResponse<JDataEntry>> addEntry(String body) {
        return repository.addEntry(body);
    }

    public LiveData<JResponse<List<List<JDataEntry>>>> refreshEntries() {
        mutableLiveData = repository.getEntries();
        return mutableLiveData;
    }
}
