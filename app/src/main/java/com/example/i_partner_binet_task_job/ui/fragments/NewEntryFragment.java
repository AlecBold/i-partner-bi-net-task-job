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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.i_partner_binet_task_job.MyApplication;
import com.example.i_partner_binet_task_job.R;
import com.example.i_partner_binet_task_job.pojo.responses.JDataEntry;
import com.example.i_partner_binet_task_job.pojo.responses.JResponse;
import com.example.i_partner_binet_task_job.ui.viewmodel.EntryViewModel;

import java.util.List;

import javax.inject.Inject;

public class NewEntryFragment extends Fragment {

    @Inject
    public EntryViewModel viewModel;

    private Button saveBtn;
    private Button cancelBtn;
    private EditText bodyTxt;
    private RelativeLayout progressBarLayout;

    public NewEntryFragment() {
    }

    public static NewEntryFragment newInstance() {
        NewEntryFragment fragment = new NewEntryFragment();
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
        View view = inflater.inflate(R.layout.fragment_new_entry, container, false);
        initViews(view);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                progressBarLayout.setVisibility(View.VISIBLE);
                bodyTxt.clearFocus();
                viewModel.addEntry(bodyTxt.getText().toString()).observe(getActivity(), new Observer<JResponse<JDataEntry>>() {
                    @Override
                    public void onChanged(JResponse<JDataEntry> jDataEntryJResponse) {
                        viewModel.refreshEntries().observe(getActivity(), new Observer<JResponse<List<List<JDataEntry>>>>() {
                            @Override
                            public void onChanged(JResponse<List<List<JDataEntry>>> listJResponse) {
                                Navigation.findNavController(view).navigate(R.id.action_newEntryFragment_to_listEntryFragment);
                            }
                        });
                    }
                });
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bodyTxt.clearFocus();
                Navigation.findNavController(view).navigate(R.id.action_newEntryFragment_to_listEntryFragment);
            }
        });
        return view;
    }

    private void initViews(View view) {
        saveBtn = view.findViewById(R.id.btnSave);
        cancelBtn = view.findViewById(R.id.btnCancel);
        bodyTxt = view.findViewById(R.id.txtBody);
        progressBarLayout = view.findViewById(R.id.progressBarLayout);
    }

}