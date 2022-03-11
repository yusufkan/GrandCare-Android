package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Authentication extends AppCompatActivity {

    private ImageView ileri3_btn, geri3_btn;
    protected EditText editTCidentity, editIdentityDoc;
    protected String strTCidentity, strIdentityDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        editTCidentity = (EditText) findViewById(R.id.editTC);
        editIdentityDoc = (EditText) findViewById(R.id.uploadfile);

        //İleri butonu
        ileri3_btn = findViewById(R.id.ileri3_btn);
        ileri3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Objede tutulan değerleri string cinsinden değişkenlere atıyoruz
                strTCidentity = editTCidentity.getText().toString();
                strIdentityDoc = editIdentityDoc.getText().toString();

             //Boş alan kontrolü
                if (!TextUtils.isEmpty(strTCidentity) || !TextUtils.isEmpty(strIdentityDoc)) {

                    //tcNo doğruluk kontrolü
                    if(tcNoSorgula(strTCidentity)){
                        ((GlobalVariables) getApplication()).setTcNo(strTCidentity);
                        Intent intentIleri1 = new Intent(Authentication.this, SignupMailPhone.class);
                        startActivity(intentIleri1);

                    }else{
                        Toast.makeText(Authentication.this, "Tc no hatalı", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Authentication.this, "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Geri butonu
        geri3_btn = findViewById(R.id.geri3_btn);
        geri3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGeri1 = new Intent(Authentication.this, SignupUser.class);
                startActivity(intentGeri1);
            }
        });
    }

    //tcNo sorgulama metodu
    public Boolean tcNoSorgula(String tcNo){
        if (tcNo.length() != 11) return false;

        int toplam=0, kontrol=0;
        char[] charArray;
        int[] intArray = new int[11];

        charArray = tcNo.toCharArray();

        for(int i =0; i<11; i++){
            intArray[i] = charArray[i] - '0';
            toplam += intArray[i];
        }

        if(intArray[0] == 0) return false;
        if(intArray[10] % 2 == 1) return false;
        if(((intArray[0] + intArray[2] + intArray[4] + intArray[6] + intArray[8]) * 7 - (intArray[1] + intArray[3] + intArray[5] + intArray[7])) % 10 != intArray[9]) return false;
        if((intArray[0] + intArray[1] + intArray[2] + intArray[3] + intArray[4] + intArray[5] + intArray[6] + intArray[7] + intArray[8] + intArray[9]) % 10 != intArray[10]) return false;

        return true;
    }
}