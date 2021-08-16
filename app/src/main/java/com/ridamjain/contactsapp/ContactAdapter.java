package com.ridamjain.contactsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements Filterable {
    Activity activity;
    ArrayList<ContactList>  contactLists;
    ArrayList<ContactList>  searchcontact;

    public ContactAdapter(Activity activity, ArrayList<ContactList> contactLists) {
        this.activity = activity;
        this.contactLists = contactLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontact,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
ContactList model=contactLists.get(position);
holder.cn.setText(model.getName());
holder.cnum.setText(model.getNumber());
    }

    @Override
    public int getItemCount() {
        return contactLists.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ContactList> model = new ArrayList<>();
            if (constraint ==null || constraint.length() == 0 )
            {
                model.addAll(contactLists);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ContactList item : contactLists) {
                    if (item.getName().toLowerCase().contains(filterPattern) || item.getNumber().contains(filterPattern)) {
                        model.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = model;
            return results;
            }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
contactLists.clear();
contactLists.addAll((ArrayList) results.values);
notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cn,cnum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        cn = itemView.findViewById(R.id.cname);
        cnum = itemView.findViewById(R.id.num);
        }
    }
    public void filterList(ArrayList<ContactList> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        contactLists = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
    }
}
