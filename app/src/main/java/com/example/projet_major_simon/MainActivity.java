package com.example.projet_major_simon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.projet_major_simon.databinding.ActivityMainBinding;
import com.example.projet_major_simon.http.RetrofitCookie;
import com.example.projet_major_simon.http.RetrofitUtil;
import com.example.projet_major_simon.http.Service;
import com.example.projet_major_simon.http.ServiceCookie;
import com.example.projet_major_simon.transfer.SigninRequest;
import com.example.projet_major_simon.transfer.SignupRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    ProgressDialog progressD;
    ServiceCookie service;
    SigninRequest signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle(getString(R.string.connexion_title));
        setContentView(view);





        service = RetrofitCookie.post();
        final EditText et_u = findViewById(R.id.Edit_Text_Username);
        final EditText et_p = findViewById(R.id.Edit_Text_Password);

        binding.buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(i);
            }
        });

        binding.buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin = new SigninRequest();
                signin.username = et_u.getText().toString();
                signin.password = et_p.getText().toString();





                    progressD = ProgressDialog.show(MainActivity.this, "Please wait",
                            "Long operation starts...", true);


        new DialogTask<>().execute();


            }
        });
    }

    class DialogTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {
            super.onPostExecute(c);

            service.Signinrequest(signin).enqueue(new Callback<SigninRequest>() {
                @Override
                public void onResponse(Call<SigninRequest> call, Response<SigninRequest> response) {
                    if (response.isSuccessful()) {

                        SignupRequest resultat = response.body();
                        final String user = resultat.username;

                        Intent i = new Intent(MainActivity.this, AccueilActivity.class);
                        i.putExtra("username",user);

                        progressD.dismiss();

                        Log.i("sleep","sleep 2 sec");

                        startActivity(i);

                    }else {
                        Log.i("RETROFIT", response.code()+"");
                        progressD.dismiss();

                    }
                }

                @Override
                public void onFailure(Call<SigninRequest> call, Throwable t) {
                    Log.i("RETROFIT", t.getMessage());
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