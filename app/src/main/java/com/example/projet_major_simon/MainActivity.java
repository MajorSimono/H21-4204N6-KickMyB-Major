package com.example.projet_major_simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Connexion");
        setContentView(view);


        final ServiceCookie service = RetrofitCookie.post();
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
                SigninRequest signin = new SigninRequest();
                signin.username = et_u.getText().toString();
                signin.password = et_p.getText().toString();


                service.Signinrequest(signin).enqueue(new Callback<SigninRequest>() {
                    @Override
                    public void onResponse(Call<SigninRequest> call, Response<SigninRequest> response) {
                        if (response.isSuccessful()) {

                            SignupRequest resultat = response.body();
                            final String user = resultat.username;

                            Intent i = new Intent(MainActivity.this, AccueilActivity.class);
                            i.putExtra("username",user);
                            startActivity(i);

                        }else {
                            Log.i("RETROFIT", response.code()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<SigninRequest> call, Throwable t) {
                        Log.i("RETROFIT", t.getMessage());
                    }
                });
            }
        });
    }
}