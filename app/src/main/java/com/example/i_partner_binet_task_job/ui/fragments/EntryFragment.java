package com.example.i_partner_binet_task_job.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.i_partner_binet_task_job.MyApplication;
import com.example.i_partner_binet_task_job.R;
import com.example.i_partner_binet_task_job.pojo.responses.JDataEntry;
import com.example.i_partner_binet_task_job.pojo.responses.JResponse;
import com.example.i_partner_binet_task_job.ui.viewmodel.EntryViewModel;
import com.example.i_partner_binet_task_job.utils.DateConverter;

import java.util.List;

import javax.inject.Inject;

public class EntryFragment extends Fragment {
    public static final String KEY_ID = "key_id";

    private TextView txtBody;
    private TextView txtCreated;
    private TextView txtEdited;

    @Inject
    public EntryViewModel viewModel;

    public EntryFragment() {
    }

    public static EntryFragment newInstance() {
        EntryFragment fragment = new EntryFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApplication) requireActivity().getApplication()).getAppComponent().entrySubComponent().create().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);
        initViews(view);
        int id = getArguments().getInt(KEY_ID);
        viewModel.getEntries().observe(getActivity(), new Observer<JResponse<List<List<JDataEntry>>>>() {
            @Override
            public void onChanged(JResponse<List<List<JDataEntry>>> listJResponse) {
                JDataEntry jDataEntry = listJResponse.getData().get(0).get(id);
                txtBody.setText(jDataEntry.getBody());
                txtCreated.setText(DateConverter.humanReadableDateFromSeconds(Long.parseLong(jDataEntry.getCreated())));
                txtEdited.setText(DateConverter.humanReadableDateFromSeconds(Long.parseLong(jDataEntry.getEdited())));
            }
        });
        return view;
    }
    private void initViews(View view) {
        txtBody = view.findViewById(R.id.txtBody);
        txtCreated = view.findViewById(R.id.txtCreated);
        txtEdited = view.findViewById(R.id.txtEdited);
    }
}