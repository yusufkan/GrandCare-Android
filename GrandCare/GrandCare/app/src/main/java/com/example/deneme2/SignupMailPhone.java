package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignupMailPhone extends AppCompatActivity {

    //Değişken tanımları
    private ImageView ileri4_btn, geri4_btn;
    protected EditText editMail, editPhone;
    protected String strMail, strPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupmailphone);

        editMail = (EditText) findViewById(R.id.kayit_email);
        editPhone = (EditText) findViewById(R.id.kayit_telefon);

        //İleri butonu
        ileri4_btn = findViewById(R.id.ileri4_btn);
        ileri4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strMail = editMail.getText().toString();
                strPhone = editPhone.getText().toString();

            //Boş alan kontrolü
                if (!TextUtils.isEmpty(strMail) || !TextUtils.isEmpty(strPhone)) {
                    ((GlobalVariables) getApplication()).getMail();
                    ((GlobalVariables) getApplication()).getTelNo();

                    Intent intentIleri4 = new Intent(SignupMailPhone.this, CreateProfile.class);
                    intentIleri4.putExtra("Eposta",strMail);
                    startActivity(intentIleri4);

                } else {
                    Toast.makeText(SignupMailPhone.this, "Telefon ya da e-mail bilgilerinden en az birinin doldurulması gerekmektedir.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //Geri butonu
        geri4_btn = findViewById(R.id.geri4_btn);
        geri4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGeri4 = new Intent(SignupMailPhone.this, Authentication.class);
                startActivity(intentGeri4);
            }
        });
    }
}