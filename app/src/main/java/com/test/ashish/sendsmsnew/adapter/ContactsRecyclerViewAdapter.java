package com.test.ashish.sendsmsnew.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.ashish.sendsmsnew.R;
import com.test.ashish.sendsmsnew.pojo.Contact;

import java.util.ArrayList;
import java.util.List;


public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Contact> data;
    private ContactsRecyclerViewAdapter.ClickListener clickListener;

    public ContactsRecyclerViewAdapter(ClickListener clickListener, ArrayList<Contact> data) {
        this.clickListener = clickListener;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).name);
        holder.txtMobile.setText(data.get(position).mobile);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtMobile;
        private LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtMobile = itemView.findViewById(R.id.txtMobile);
            linearLayout = itemView.findViewById(R.id.linearLayout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(data.get(getAdapterPosition()).name,data.get(getAdapterPosition()).mobile);
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent(String userName, String userMobile);
    }

    public void setData(List<Contact> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}