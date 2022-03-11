package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class SignupName extends AppCompatActivity {

    private ImageView ileri1_btn, geri1_btn;
    protected EditText editName, editLastname, editBirthday, editCity;
    protected String strName, strLastname, strBirthday, strCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupname);

        editName = (EditText) findViewById(R.id.kayit_ad);
        editLastname = (EditText) findViewById(R.id.kayit_soyad);
        editCity = (EditText) findViewById(R.id.kayit_sehir);
        editBirthday = (EditText) findViewById(R.id.kayit_dogumtarihi);

        //ileri butonu
        ileri1_btn = findViewById(R.id.ileri1_btn);
        ileri1_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                strName = editName.getText().toString();
                strLastname = editLastname.getText().toString();
                strBirthday = editBirthday.getText().toString();
                strCity = editCity.getText().toString();

                //Boş alan kontrol koşulu
                if (!TextUtils.isEmpty(strName) && !TextUtils.isEmpty(strLastname) && !TextUtils.isEmpty(strBirthday) && !TextUtils.isEmpty(strCity)) {

                    //Aldığımız bilgileri global değişkenlere atıyoruz
                    ((GlobalVariables) getApplication()).setAd(strName);
                    ((GlobalVariables) getApplication()).setSoyad(strLastname);
                    ((GlobalVariables) getApplication()).setDogumTarihi(strBirthday);
                    ((GlobalVariables) getApplication()).setSehir(strCity);

                    Intent intentIleri1 = new Intent(SignupName.this, SignupUser.class);
                    startActivity(intentIleri1);
                } else {
                    Toast.makeText(SignupName.this, "Tüm alanları doldurduğunuzdan emin olunuz!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Geri butonu
        geri1_btn = findViewById(R.id.geri1_btn);
        geri1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGeri1 = new Intent(SignupName.this, Login.class);
                startActivity(intentGeri1);
            }
        });

        /*DOĞUM TARİHİ ile datepickerdialog*/
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //Doğum tarihi seçim ekranı
        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupName.this, R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;

                        //Pickerdan seçilen tarihi string olarak tutuyoruz.
                        String date = day + "/" + month + "/" + year;
                        editBirthday.setText(date);
                    }
                }, year, month, day);
                //Seçilen tarihi panelde string olarak gösteriyoruz.
                datePickerDialog.show();
            }
        });
    }
}