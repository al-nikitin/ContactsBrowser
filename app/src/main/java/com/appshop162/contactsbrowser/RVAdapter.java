package com.appshop162.contactsbrowser;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ContactsViewHolder> {

    public ArrayList<Contact> contacts;

    public RVAdapter(ArrayList<Contact> contacts) {
        super();
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public RVAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_element, viewGroup, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ContactsViewHolder contactsViewHolder, int i) {
        Contact contact = contacts.get(i);
        contactsViewHolder.tvName.setText(contact.name);
        contactsViewHolder.tvNumber.setText(contact.number);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView tvName, tvNumber;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.contact_photo);
            tvName = (TextView) itemView.findViewById(R.id.contact_name);
            tvNumber = (TextView) itemView.findViewById(R.id.contact_number);
        }
    }

    public void addContactToRV(Contact contact) {
        contacts.add(contact);
        notifyDataSetChanged();
    }
}
