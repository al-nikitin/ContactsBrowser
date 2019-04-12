package com.appshop162.contactsbrowser;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvContacts;
    private RVAdapter rvAdapter;
    private ArrayList<Contact> contactsFull, contactsFiltered;

    private String numberEntered = "";
    private TextView tvNumber;

    private static final int PERMISSIONS_REQUEST_CODE = 1240;
    String[] appPermissions = {
            Manifest.permission.READ_CONTACTS
    };
    List<String> listPermissionsNeeded = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (String permission : appPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) listPermissionsNeeded.add(permission);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSIONS_REQUEST_CODE);
        } else startApp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean allPermissionsGranted = true;
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) allPermissionsGranted = false;
        }
        if (allPermissionsGranted) startApp();
    }

    public void startApp() {

        rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button0 = (Button) findViewById(R.id.button0);
        Button buttonStar = (Button) findViewById(R.id.buttonStar);
        Button buttonHash = (Button) findViewById(R.id.buttonHash);
        Button buttonBackspace = (Button) findViewById(R.id.buttonBackspace);

        contactsFull = getContactList();
        contactsFiltered = new ArrayList<>();

        // setup for RecyclerView
        rvAdapter = new RVAdapter(contactsFiltered);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvContacts.setAdapter(rvAdapter);
        rvContacts.setLayoutManager(layoutManager);

        filterContacts();

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("9");
            }
        });
        buttonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("*");
            }
        });
        buttonHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("#");
            }
        });
        buttonBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberEntered.length() > 0) {
                    numberEntered = numberEntered.substring(0, numberEntered.length() - 1);
                    tvNumber.setText(numberEntered);
                    filterContacts();
                }
            }
        });
        button0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addSymbol("+");
                return false;
            }
        });
    }

    private void addSymbol(String symbol) {
        numberEntered += symbol;
        tvNumber.setText(numberEntered);
        filterContacts();
    }

    private void filterContacts() {
        contactsFiltered = new ArrayList<>();
        for (Contact contact : contactsFull) {
            if (contact.number.contains(numberEntered) || contact.nameAsNumbers.contains(numberEntered)) {
                contactsFiltered.add(contact);
            }
        }
        rvAdapter.contacts = contactsFiltered;
        rvAdapter.numberEntered = numberEntered;
        rvAdapter.notifyDataSetChanged();
    }

    private ArrayList<Contact> getContactList() {
        ArrayList<Contact> contactList = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor != null && cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Bitmap photo = null;

                        try {
                            InputStream inputStream =
                                    ContactsContract.Contacts.openContactPhotoInputStream(contentResolver,
                                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id)));
                            if (inputStream != null) {
                                photo = BitmapFactory.decodeStream(inputStream);
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Contact contact = new Contact(name, number);
                        contact.photo = photo;
                        contactList.add(contact);
                    }
                    pCur.close();
                }
            }
        }
        if (cursor != null) cursor.close();
        return contactList;
    }
}
