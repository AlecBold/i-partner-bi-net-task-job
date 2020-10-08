package com.example.i_partner_binet_task_job.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.i_partner_binet_task_job.MyApplication;
import com.example.i_partner_binet_task_job.R;
import com.example.i_partner_binet_task_job.ui.viewmodel.SessionViewModel;

import javax.inject.Inject;

public class LoadFragment extends Fragment {

    @Inject
    public SessionViewModel viewModel;

    public LoadFragment() {
    }

    public static LoadFragment newInstance() {
        LoadFragment fragment = new LoadFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApplication)requireActivity().getApplication()).getAppComponent().loadSubComponent().create().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_load, container, false);
        viewModel.getSession().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Navigation.findNavController(view).navigate(R.id.action_loadFragment_to_listEntryFragment);
            }
        });
        return view;
    }
}