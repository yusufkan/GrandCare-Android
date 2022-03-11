package com.example.deneme2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignupUser extends SignupName {

    private ImageView ileri2_btn,geri2_btn;
    protected EditText editUsername, editPassword, editPasswordagain;
    protected String strUsername, strPassword, strPasswordagain;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupuser);

        editUsername = (EditText)findViewById(R.id.kayit_kullaniciad);
        editPassword = (EditText)findViewById(R.id.kayit_sifre1);
        editPasswordagain = (EditText)findViewById(R.id.kayit_sifre2);
        auth=FirebaseAuth.getInstance();

        //İleri butonu
        ileri2_btn = findViewById(R.id.ileri2_btn);
        ileri2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strUsername = editUsername.getText().toString();
                strPassword = editPassword.getText().toString();
                strPasswordagain = editPasswordagain.getText().toString();

                if (!TextUtils.isEmpty(strPassword) && !TextUtils.isEmpty(strPasswordagain) && !TextUtils.isEmpty(strUsername))
                    if (TextUtils.equals(strPassword,strPasswordagain)) {

                        ((GlobalVariables) getApplication()).setUserName(strUsername);
                        ((GlobalVariables) getApplication()).setPassword(strPassword);

                        Intent intentIleri2 = new Intent(SignupUser.this, Authentication.class);
                        startActivity(intentIleri2);

                    }else{
                        Toast.makeText(SignupUser.this, "Şifreler eşleşmiyor", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignupUser.this, "Tüm alanları doldurduğunuzdan emin olunuz.", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        //Geri butonu
        geri2_btn = findViewById(R.id.geri2_btn);
        geri2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGeri2 = new Intent(SignupUser.this, SignupName.class);
                startActivity(intentGeri2);
            }
        });
    }
}