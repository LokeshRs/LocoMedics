package com.example.locomedics;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class Retrieve extends AppCompatActivity {
    ListView listView;
    FirebaseListAdapter adapter;
    TextView name;
    TextView company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_retrieve);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    listView = findViewById(R.id.list);
        Query query = FirebaseDatabase.getInstance().getReference();
        FirebaseListOptions<Medic> options = new FirebaseListOptions.Builder<Medic>()
                .setLayout(R.layout.med_item)
                .setQuery(query,Medic.class)
                .setLifecycleOwner(this)
                .build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {

                Medic medic = (Medic) model;
                name = v.findViewById(R.id.name);
                company = v.findViewById(R.id.company);
                name.setText(medic.getMed_name());
                company.setText(medic.getCompany());
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
