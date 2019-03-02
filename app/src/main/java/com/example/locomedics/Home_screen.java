package com.example.locomedics;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Home_screen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase firebaseDatabase;
    Button search;
    Medic medic;
    FirebaseListAdapter firebaseListAdapter;
    FirebaseListAdapter firebaseListAdapter1;
    TextView name;
    TextView company;
    ImageView medic_image;
    DatabaseReference ref;

    ListView medic_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_screen.this, CartActivity.class));
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        medic_list = findViewById(R.id.medic_list);
        ref=FirebaseDatabase.getInstance().getReference();


    }
    protected void onStart(){
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference();
        FirebaseListOptions<Medic> options = new FirebaseListOptions.Builder<Medic>()
                .setLayout(R.layout.med_item)
                .setQuery(query, Medic.class)
                .setLifecycleOwner(this)
                .build();
        firebaseListAdapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {

                medic = (Medic) model;
                name = v.findViewById(R.id.name);
                company = v.findViewById(R.id.company);
                medic_image = v.findViewById(R.id.medic_image);
                name.setText(medic.getMed_name());
//<<<<<<< HEAD
                company.setText("Company: "+medic.getCompany());
                String url =medic.getImage();
//>>>>>>> 499d6f6cc575c913e60235a77cec9f08cd90caad
                Picasso.get().load(url).into(medic_image);
            }

        };

        medic_list.setAdapter(firebaseListAdapter);
        medic_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Home_screen.this,DialogBox.class);
                intent.putExtra("name",medic.getMed_name());
                intent.putExtra("company",medic.getCompany());
                intent.putExtra("description",medic.getMed_discription());
                intent.putExtra("generic_name",medic.getMed_genericname());
                intent.putExtra("image",medic.getImage());
                startActivity(intent);
            }
        });
    }

    private void firebasearch(String search){
        Query firebasesearchquery=ref.orderByChild("Med_name").startAt(search).endAt(search+"\uf8ff");
        FirebaseListOptions<Medic>firebaseListOptions=new FirebaseListOptions.Builder<Medic>()
                .setLayout(R.layout.med_item)
                .setQuery(firebasesearchquery, Medic.class)
                .setLifecycleOwner(this)
                .build();
        firebaseListAdapter=new FirebaseListAdapter(firebaseListOptions) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                Medic medic = (Medic) model;
                name = v.findViewById(R.id.name);
                company = v.findViewById(R.id.company);
                medic_image = v.findViewById(R.id.medic_image);
                name.setText(medic.getMed_name());
                company.setText("Company "+medic.getCompany());
                String url =medic.getImage();
                Picasso.get().load(url).into(medic_image);

            }
        };
        medic_list.setAdapter(firebaseListAdapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebasearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebasearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(Home_screen.this,Home_screen.class));
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(Home_screen.this,CartActivity.class));
        } else if (id == R.id.categories) {

        } else if (id == R.id.about) {

        }else if (id == R.id.nav_Tc) {

        }else if (id == R.id.mail) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    public void list() {
    }



}
