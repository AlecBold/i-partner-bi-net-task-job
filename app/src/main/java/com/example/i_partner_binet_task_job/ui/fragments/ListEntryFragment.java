package com.example.i_partner_binet_task_job.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.i_partner_binet_task_job.MyApplication;
import com.example.i_partner_binet_task_job.R;
import com.example.i_partner_binet_task_job.pojo.responses.JDataEntry;
import com.example.i_partner_binet_task_job.pojo.responses.JResponse;
import com.example.i_partner_binet_task_job.ui.adapter.EntriesRecycleViewAdapter;
import com.example.i_partner_binet_task_job.ui.viewmodel.EntryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

public class ListEntryFragment extends Fragment {

    private FloatingActionButton newEntryBtn;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private EntriesRecycleViewAdapter adapter;

    @Inject
    public EntryViewModel viewModel;

    public ListEntryFragment() {
    }

    public static ListEntryFragment newInstance() {
        ListEntryFragment fragment = new ListEntryFragment();
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
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_entry, container, false);
        initViews(view);

        newEntryBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listEntryFragment_to_newEntryFragment));

        adapter = new EntriesRecycleViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        viewModel.getEntries().observe(getActivity(), new Observer<JResponse<List<List<JDataEntry>>>>() {
            @Override
            public void onChanged(JResponse<List<List<JDataEntry>>> listJResponse) {
                if (listJResponse != null) {
                    adapter.setEntryList(listJResponse.getData().get(0));
                } else {
                    //TODO: implement handler error
                }
            }
        });

        newEntryBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listEntryFragment_to_newEntryFragment));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refreshEntries().observe(getActivity(), new Observer<JResponse<List<List<JDataEntry>>>>() {
                    @Override
                    public void onChanged(JResponse<List<List<JDataEntry>>> listJResponse) {
                        adapter.setEntryList(listJResponse.getData().get(0));
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        return view;
    }

    private void initViews(View view) {
        newEntryBtn = view.findViewById(R.id.btnNewEntry);
        refreshLayout = view.findViewById(R.id.swipeRefresh);

        recyclerView = view.findViewById(R.id.recyclerEntries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vertical_divider));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
}