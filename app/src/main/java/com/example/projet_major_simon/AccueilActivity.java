package com.example.projet_major_simon;

import android.content.Intent;
import android.os.Bundle;

import com.example.projet_major_simon.databinding.ActivityAccueilBinding;
import com.example.projet_major_simon.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.sql.Date;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

public class AccueilActivity extends AppCompatActivity {

    TacheAdapter adapter;

 private ActivityAccueilBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Accueil");
        setContentView(view);
        this.initReycler();
        this.remplirRecycler();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
                    startActivity(i);
                }




        });
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