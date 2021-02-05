package com.example.projet_major_simon;




import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_major_simon.databinding.ActivityInscriptionBinding;
import com.example.projet_major_simon.databinding.ActivityMainBinding;

public class InscriptionActivity extends AppCompatActivity {
    private ActivityInscriptionBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Inscription");
        setContentView(view);
    }
}
