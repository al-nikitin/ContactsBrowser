package com.appshop162.contactsbrowser;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ContactsViewHolder> {

    public ArrayList<Contact> contacts;
    public String numberEntered;

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
        String name = contact.name;
        String number = contact.number;
        String nameAsNumbers = contact.nameAsNumbers;

        if (numberEntered.length() > 0 && nameAsNumbers.contains(numberEntered)) {
            SpannableString nameSS = new SpannableString(name);

            String nameClone = nameAsNumbers;
            int zeroIndex = 0;

            while (nameClone.contains(numberEntered)) {
                int start = zeroIndex + nameClone.indexOf(numberEntered);
                int end = start + numberEntered.length();
                nameSS.setSpan(new RelativeSizeSpan(1.5f), start, end, 0);
                nameSS.setSpan(new ForegroundColorSpan(Color.RED), start, end, 0);
                nameClone = nameAsNumbers.substring(end);
                zeroIndex = end;
            }
            contactsViewHolder.tvName.setText(nameSS);
        } else contactsViewHolder.tvName.setText(name);

        if (numberEntered.length() > 0 && number.contains(numberEntered)) {
            SpannableString numberSS = new SpannableString(number);

            String numberClone = number;
            int zeroIndex = 0;

            while (numberClone.contains(numberEntered)) {
                int start = zeroIndex + numberClone.indexOf(numberEntered);
                int end = start + numberEntered.length();
                numberSS.setSpan(new RelativeSizeSpan(1.5f), start, end, 0);
                numberSS.setSpan(new ForegroundColorSpan(Color.RED), start, end, 0);
                numberClone = number.substring(end);
                zeroIndex = end;
            }
            contactsViewHolder.tvNumber.setText(numberSS);
        } else contactsViewHolder.tvNumber.setText(contact.number);

        if (contact.photo == null) {
            System.out.println("sddsfdsfdsfcrfcdsds");
        } else contactsViewHolder.photo.setImageBitmap(contact.photo);
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
