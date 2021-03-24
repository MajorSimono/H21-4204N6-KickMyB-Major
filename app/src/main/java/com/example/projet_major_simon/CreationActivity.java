package com.example.projet_major_simon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet_major_simon.databinding.ActivityCreationBinding;
import com.example.projet_major_simon.http.RetrofitCookie;
import com.example.projet_major_simon.http.RetrofitUtil;
import com.example.projet_major_simon.http.Service;
import com.example.projet_major_simon.http.ServiceCookie;
import com.example.projet_major_simon.transfer.AddTaskRequest;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreationActivity extends AppCompatActivity {

    private ActivityCreationBinding binding;
    private ActionBarDrawerToggle abToggle;


    EditText date;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Création de tâche");
        setContentView(view);

        final ServiceCookie service = RetrofitCookie.post();

        TextView user = binding.navView.getHeaderView(0).findViewById(R.id.Text_UserNameDrawer);
        user.setText(getIntent().getStringExtra("username"));



        binding.buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = binding.EditTextTachename.getText().toString();
                final String dateech = binding.date.getText().toString();

              AddTaskRequest task =  new AddTaskRequest();
              task.name = name;
                try {
                    task.deadLine = new SimpleDateFormat("dd/MM/yyyy").parse(dateech);
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                service.AddTask(task).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Intent i = new Intent(CreationActivity.this, AccueilActivity.class);
                        i.putExtra("username", getIntent().getStringExtra("username"));
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(CreationActivity.this,"sa marche pas",Toast.LENGTH_LONG).show();
                    }
                });





            }
        });


        date = (EditText) findViewById(R.id.date);
        //credit du code pour la selection de la date https://abhiandroid.com/ui/datepicker#:~:text=In%20Android%2C%20DatePicker%20is%20a,provides%20timepicker%20to%20select%20time.
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(CreationActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();



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
                    Intent i = new Intent(CreationActivity.this, AccueilActivity.class);
                    i.putExtra("username", getIntent().getStringExtra("username"));

                    startActivity(i);
                }
                if (id == R.id.nav_item_CreattionTache){
                    Intent i = new Intent(CreationActivity.this, CreationActivity.class);
                    i.putExtra("username", getIntent().getStringExtra("username"));

                    startActivity(i);
                }
                if (id == R.id.nav_item_deconnexion){
                    service.Signoutrequest().enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Intent i = new Intent(CreationActivity.this, MainActivity.class);
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

}