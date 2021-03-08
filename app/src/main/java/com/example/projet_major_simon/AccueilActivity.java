package com.example.projet_major_simon;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.projet_major_simon.databinding.ActivityAccueilBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.sql.Date;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;


public class AccueilActivity extends AppCompatActivity  {

    TacheAdapter adapter;

 private ActivityAccueilBinding binding;
 private ActionBarDrawerToggle abToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Accueil");
        setContentView(view);

    TextView user = binding.navView.getHeaderView(0).findViewById(R.id.Text_UserNameDrawer);
      user.setText(getIntent().getStringExtra("username"));

        this.initReycler();
        this.remplirRecycler();




       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
                    startActivity(i);
                }




        });

        NavigationView nv = binding.navView;
        DrawerLayout dl = binding.drawerLayout;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        abToggle = new ActionBarDrawerToggle(this,dl,R.string.drawer_open,R.string.drawer_clode){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        dl.addDrawerListener(abToggle);
        abToggle.syncState();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              int id=item.getItemId();
              if (id == R.id.nav_item_Accueil){
                  Intent i = new Intent(AccueilActivity.this, AccueilActivity.class);
                  startActivity(i);
              }
                if (id == R.id.nav_item_CreattionTache){
                    Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
                    startActivity(i);
                }
                if (id == R.id.nav_item_deconnexion){
                    Intent i = new Intent(AccueilActivity.this, MainActivity.class);
                    startActivity(i);
                }

                return false;
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (abToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        abToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        abToggle.onConfigurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }


    private void  remplirRecycler() {
        for (int i =0 ; i < 200 ; i++) {
            Tache T = new Tache();
            T.name = "Tâche" + i;
            T.pourcentage = 0 + (new Random().nextInt(101));
            T.tempsEcoule = 0 + (new Random().nextInt(8));
            T.dateLimite =  new Date(121,3,20);
            adapter.list.add(T)  ;
        }
        adapter.notifyDataSetChanged();
    }

    private void initReycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TacheAdapter();
        recyclerView.setAdapter(adapter);

    }



}