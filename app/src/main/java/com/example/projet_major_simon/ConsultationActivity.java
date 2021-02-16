package com.example.projet_major_simon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.projet_major_simon.databinding.ActivityConsultationBinding;
import com.google.android.material.navigation.NavigationView;

public class ConsultationActivity extends AppCompatActivity {



    private ActivityConsultationBinding binding;
    private ActionBarDrawerToggle abToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConsultationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Consultation");
        setContentView(view);


        binding.tvName.setText(getIntent().getStringExtra("name"));
        binding.tvDateEche.setText(getIntent().getStringExtra("Date"));
        binding.tvTempsEcoule.setText(getIntent().getStringExtra("TempsE"));
        binding.tvPourcentage.setText(getIntent().getStringExtra("Pourcentage"));

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsultationActivity.this, AccueilActivity.class);
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
                    Intent i = new Intent(ConsultationActivity.this, AccueilActivity.class);
                    startActivity(i);
                }
                if (id == R.id.nav_item_CreattionTache){
                    Intent i = new Intent(ConsultationActivity.this, CreationActivity.class);
                    startActivity(i);
                }
                if (id == R.id.nav_item_deconnexion){
                    Intent i = new Intent(ConsultationActivity.this, MainActivity.class);
                    startActivity(i);
                }

                return false;
            }
        });
    }
}