package com.example.projet_major_simon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.projet_major_simon.databinding.ActivityConsultationBinding;
import com.example.projet_major_simon.http.RetrofitCookie;
import com.example.projet_major_simon.http.RetrofitUtil;
import com.example.projet_major_simon.http.Service;
import com.example.projet_major_simon.http.ServiceCookie;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultationActivity extends AppCompatActivity {



    private ActivityConsultationBinding binding;
    private ActionBarDrawerToggle abToggle;
    ServiceCookie serviceGet;
    ProgressDialog progressD;
    ServiceCookie service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConsultationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle(getString(R.string.Consultation_title));
        setContentView(view);

        service = RetrofitCookie.post();
        serviceGet = RetrofitCookie.get();


        TextView user = binding.navView.getHeaderView(0).findViewById(R.id.Text_UserNameDrawer);
        user.setText(getIntent().getStringExtra("username"));



        binding.tvName.setText(getIntent().getStringExtra("name"));
        binding.tvDateEche.setText(getIntent().getStringExtra("Date"));
        binding.tvTempsEcoule.setText(getIntent().getStringExtra("TempsE"));
        binding.tvPourcentage.setText(getIntent().getStringExtra("Pourcentage"));


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Integer pour = Integer.parseInt(binding.tvPourcentage.getText().toString());

                progressD = ProgressDialog.show(ConsultationActivity.this, "Please wait",
                        "Long operation starts...", true);


                if (pour >= 0 & pour <= 100)
                {
                    new DialogTask<>().execute();
                }
                else {
                    progressD.dismiss();

                    Toast.makeText(ConsultationActivity.this,"Percentage need to be between 0 and 100",Toast.LENGTH_SHORT).show();
                }

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
                    i.putExtra("username", getIntent().getStringExtra("username"));

                    startActivity(i);
                }
                if (id == R.id.nav_item_CreattionTache){
                    Intent i = new Intent(ConsultationActivity.this, CreationActivity.class);
                    i.putExtra("username", getIntent().getStringExtra("username"));

                    startActivity(i);
                }
                if (id == R.id.nav_item_deconnexion){
                    progressD = ProgressDialog.show(ConsultationActivity.this, "Please wait",
                            "Long operation starts...", true);
                    // start the task that will stop it
                    new logoutTask<>().execute();
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

    class DialogTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            serviceGet.progressChange(getIntent().getLongExtra("idtache", 0),Integer.parseInt(binding.tvPourcentage.getText().toString())).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {


                        Intent i = new Intent(ConsultationActivity.this, AccueilActivity.class);
                        i.putExtra("username", getIntent().getStringExtra("username"));
                        progressD.dismiss();

                        startActivity(i);

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(ConsultationActivity.this,"sa marche pas",Toast.LENGTH_LONG).show();
                    progressD.dismiss();


                }
            });
        }

        @Override
        protected C doInBackground(A... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    class logoutTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            service.Signoutrequest().enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Intent i = new Intent(ConsultationActivity.this, MainActivity.class);
                    startActivity(i);
                    progressD.dismiss();

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    progressD.dismiss();

                }
            });

        }

        @Override
        protected C doInBackground(A... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}