package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class TakeDate extends AppCompatActivity {

    protected EditText takeDate ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takedate);

        takeDate = (EditText) findViewById(R.id.choosedate);

        /*DOĞUM TARİHİ ile datepickerdialog*/
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        takeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TakeDate.this, R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        month = month + 1;
                        //Pickerdan seçilen tarihi string olarak tutuyoruz.
                        String date = day + "/" + month + "/" + year;
                        takeDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show(); //seçilen tarihi panelde string olarak gösteriyoruz.
            }
        });
    }
}