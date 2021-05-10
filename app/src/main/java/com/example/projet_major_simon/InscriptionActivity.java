package com.example.projet_major_simon;




import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_major_simon.databinding.ActivityInscriptionBinding;
import com.example.projet_major_simon.databinding.ActivityMainBinding;
import com.example.projet_major_simon.http.RetrofitCookie;
import com.example.projet_major_simon.http.RetrofitUtil;
import com.example.projet_major_simon.http.Service;
import com.example.projet_major_simon.http.ServiceCookie;
import com.example.projet_major_simon.transfer.SignupRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {
    private ActivityInscriptionBinding binding;
    ServiceCookie service;
    ProgressDialog progressD;
    SignupRequest signup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle(getString(R.string.Inscription_title));
        setContentView(view);


         service = RetrofitCookie.post();
        final EditText et_u = findViewById(R.id.Edit_Text_Username);
        final EditText et_p = findViewById(R.id.Edit_Text_Password);
        final EditText et_c = findViewById(R.id.Edit_Text_ConfirmationPassword);



        binding.buttonInscription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                 signup = new SignupRequest();
               signup.username = et_u.getText().toString();
                signup.password = et_p.getText().toString();
                String Pass = et_p.getText().toString();
                String ConPass = et_c.getText().toString();

                progressD = ProgressDialog.show(InscriptionActivity.this, "Please wait",
                        "Long operation starts...", true);
                // start the task that will stop it
                if (Pass.equals(ConPass))
                {
                    new DialogTask<>().execute();

                }
                else {
                    progressD.dismiss();

                    Toast.makeText(InscriptionActivity.this,"These passwords do not match",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    class DialogTask<A,B,C> extends AsyncTask<A,B,C> {

        @Override
        protected void onPostExecute(C c) {

            service.Signuprequest(signup).enqueue(new Callback<SignupRequest>() {
                @Override
                public void onResponse(Call<SignupRequest> call, Response<SignupRequest> response) {
                    if (response.isSuccessful()) {
                        progressD.dismiss();

                        SignupRequest resultat = response.body();
                        final String user = resultat.username;

                        Intent i = new Intent(InscriptionActivity.this, AccueilActivity.class);
                        i.putExtra("username",user);
                        startActivity(i);

                    }else {
                        Log.i("RETROFIT", response.code()+"");
                        Toast.makeText(InscriptionActivity.this,"username already exists",Toast.LENGTH_SHORT).show();
                        progressD.dismiss();

                    }
                }

                @Override
                public void onFailure(Call<SignupRequest> call, Throwable t) {
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
