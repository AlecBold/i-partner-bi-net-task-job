package com.example.i_partner_binet_task_job.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.i_partner_binet_task_job.R;
import com.example.i_partner_binet_task_job.pojo.responses.JDataEntry;
import com.example.i_partner_binet_task_job.ui.fragments.EntryFragment;
import com.example.i_partner_binet_task_job.utils.DateConverter;

import java.util.List;

public class EntriesRecycleViewAdapter extends RecyclerView.Adapter<EntriesRecycleViewAdapter.EntryHolder> {

    private Context context;
    private List<JDataEntry> entryList;

    public EntriesRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EntryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_entry, parent, false);
        EntryHolder holder = new EntryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntryHolder holder, final int position) {
        JDataEntry jDataEntry = entryList.get(position);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(EntryFragment.KEY_ID, position);
                Navigation.findNavController(view).navigate(R.id.action_listEntryFragment_to_entryFragment, bundle);
            }
        });
        holder.txtEdited.setText(DateConverter.humanReadableDateFromSeconds(Long.parseLong(jDataEntry.getEdited())));
        holder.txtBody.setText(jDataEntry.getBody());
    }

    @Override
    public int getItemCount() {
        if (entryList != null) {
            return entryList.size();
        }
        return 0;
    }

    public void setEntryList(List<JDataEntry> entryList) {
        this.entryList = entryList;
        notifyDataSetChanged();
    }

    public class EntryHolder extends RecyclerView.ViewHolder {

        CardView parent;
        TextView txtBody;
        TextView txtEdited;

        public EntryHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            txtBody = itemView.findViewById(R.id.txtBody);
            txtEdited = itemView.findViewById(R.id.txtEdited);
        }
    }
}
