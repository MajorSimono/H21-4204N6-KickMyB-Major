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

import com.example.projet_major_simon.http.RetrofitCookie;
import com.example.projet_major_simon.http.RetrofitUtil;
import com.example.projet_major_simon.http.Service;
import com.example.projet_major_simon.http.ServiceCookie;
import com.example.projet_major_simon.transfer.HomeItemResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
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
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccueilActivity extends AppCompatActivity  {

    TacheAdapter adapter;

 private ActivityAccueilBinding binding;
 private ActionBarDrawerToggle abToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle(getString(R.string.Accueil_Title));
        setContentView(view);

        final ServiceCookie service = RetrofitCookie.post();



        TextView user = binding.navView.getHeaderView(0).findViewById(R.id.Text_UserNameDrawer);
      user.setText(getIntent().getStringExtra("username"));

        this.initReycler();
        this.remplirRecycler();




       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
                    i.putExtra("username", getIntent().getStringExtra("username"));

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
                  i.putExtra("username", getIntent().getStringExtra("username"));
                  startActivity(i);
              }
                if (id == R.id.nav_item_CreattionTache){
                    Intent i = new Intent(AccueilActivity.this, CreationActivity.class);
                    i.putExtra("username", getIntent().getStringExtra("username"));
                    startActivity(i);
                }
                if (id == R.id.nav_item_deconnexion){

                    service.Signoutrequest().enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Intent i = new Intent(AccueilActivity.this, MainActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });



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

        final ServiceCookie serviceGet = RetrofitCookie.get();

        serviceGet.HomeItemResponse().enqueue(new Callback<List<HomeItemResponse>>() {
            @Override
            public void onResponse(Call<List<HomeItemResponse>> call, Response<List<HomeItemResponse>> response) {


                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++){
                        Tache T = new Tache();
                        T.name = response.body().get(i).name;
                        T.percentageDone = response.body().get(i).percentageDone;
                        T.percentageTimeSpent = response.body().get(i).percentageTimeSpent;
                        T.deadline =  response.body().get(i).deadline;
                        T.id = response.body().get(i).id;
                        adapter.list.add(T)  ;

                    }
                    adapter.username =  getIntent().getStringExtra("username");
                    adapter.notifyDataSetChanged();

                }else {
                    Log.i("RETROFIT", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<List<HomeItemResponse>> call, Throwable t) {
                Toast.makeText(AccueilActivity.this,"sa marche pas",Toast.LENGTH_LONG).show();
            }
        });


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