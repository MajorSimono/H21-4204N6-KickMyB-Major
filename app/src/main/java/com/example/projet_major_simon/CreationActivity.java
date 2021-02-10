package com.example.projet_major_simon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.projet_major_simon.databinding.ActivityCreationBinding;

import java.util.Calendar;

public class CreationActivity extends AppCompatActivity {

    private ActivityCreationBinding binding;

    EditText date;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setTitle("Création de tâche");
        setContentView(view);


        binding.buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreationActivity.this, AccueilActivity.class);
                startActivity(i);
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


    }
}