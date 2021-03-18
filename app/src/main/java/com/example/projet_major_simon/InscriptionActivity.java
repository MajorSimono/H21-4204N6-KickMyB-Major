package com.example.projet_major_simon;




import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_major_simon.databinding.ActivityInscriptionBinding;
import com.example.projet_major_simon.databinding.ActivityMainBinding;
import com.example.projet_major_simon.http.RetrofitUtil;
import com.example.projet_major_simon.http.Service;
import com.example.projet_major_simon.transfer.SignupRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {
    private ActivityInscriptionBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Inscription");
        setContentView(view);


        final Service service = RetrofitUtil.post();
        final EditText et_u = findViewById(R.id.Edit_Text_Username);
        final EditText et_p = findViewById(R.id.Edit_Text_Password);



        binding.buttonInscription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SignupRequest signup = new SignupRequest();
               signup.username = et_u.getText().toString();
                signup.password = et_p.getText().toString();


                service.Signuprequest(signup).enqueue(new Callback<SignupRequest>() {
                    @Override
                    public void onResponse(Call<SignupRequest> call, Response<SignupRequest> response) {
                        if (response.isSuccessful()) {

                            SignupRequest resultat = response.body();
                            final String user = resultat.username;

                            Intent i = new Intent(InscriptionActivity.this, AccueilActivity.class);
                            i.putExtra("username",user);
                            startActivity(i);

                        }else {
                            Log.i("RETROFIT", response.code()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<SignupRequest> call, Throwable t) {
                        Log.i("RETROFIT", t.getMessage());
                    }
                });



            }
        });
    }


}
