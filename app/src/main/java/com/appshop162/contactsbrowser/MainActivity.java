package com.appshop162.contactsbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvContacts;
    private RVAdapter rvAdapter;
    private ArrayList<Contact> contacts;

    private String numberEntered = "";
    private TextView tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        // populate contacts with dummy entries
        contacts = new ArrayList<>();
        contacts.add(new Contact("Alex Nikitin", "89169060517"));
        contacts.add(new Contact("Иван Петров", "+79101234567"));
        contacts.add(new Contact("Николай Васильев", "89999999999"));
//        contacts.add(new Contact("DDD DDDDD", "1111111111"));
//        contacts.add(new Contact("EEE EEEEE", "1111111111"));
//        contacts.add(new Contact("FFF FFFFF", "1111111111"));
//        contacts.add(new Contact("GGG GGGGG", "1111111111"));
//        contacts.add(new Contact("HHH HHHHH", "1111111111"));
//        contacts.add(new Contact("III IIIII", "1111111111"));

        // setup for RecyclerView
        rvAdapter = new RVAdapter(contacts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvContacts.setAdapter(rvAdapter);
        rvContacts.setLayoutManager(layoutManager);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSymbol("0");
                contacts.add(new Contact("DDD DDDDD", "1111111111"));
                rvAdapter.notifyDataSetChanged();
            }
        });

        button0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addSymbol("+");
                return false;
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
                }
            }
        });
    }

    private void addSymbol(String symbol) {
        numberEntered += symbol;
        tvNumber.setText(numberEntered);
    }
}
