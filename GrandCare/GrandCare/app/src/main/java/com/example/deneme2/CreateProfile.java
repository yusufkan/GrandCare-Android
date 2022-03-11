package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfile extends AppCompatActivity {

    //Değişken tanımlama
    private ImageView ileri5_btn, geri5_btn;
    protected EditText editsaglik;
    protected String strSaglik;

    private RadioButton other;
    private RadioGroup denemegroup;         //digerilgi radiobuttonu için tanımlandı. Böylece tekrar tıklandığında unchecked oluyor.
    EditText otherlike;

    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);

        editsaglik = (EditText) findViewById(R.id.saglik);

        //İleri butonu
        ileri5_btn = findViewById(R.id.ileri5_btn);
        ileri5_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                strSaglik = editsaglik.getText().toString();
                 //Boş alan kontrolü
                if(!TextUtils.isEmpty(strSaglik)) {

                    User abc = new User(((GlobalVariables) getApplication()).getAd(),
                            ((GlobalVariables) getApplication()).getSoyad(),
                            ((GlobalVariables) getApplication()).getSehir(),
                            ((GlobalVariables) getApplication()).getDogumTarihi(),
                            ((GlobalVariables) getApplication()).getUserName(),
                            ((GlobalVariables) getApplication()).getPassword(),
                            ((GlobalVariables) getApplication()).getTcNo(),
                            ((GlobalVariables) getApplication()).getMail(),
                            ((GlobalVariables) getApplication()).getTelNo(),
                    strSaglik);

                    //Bilgileri DB ye yazdırıyoruz
                    dr=FirebaseDatabase.getInstance().getReference();
                    dr.child("Users").child("Tc").child(((GlobalVariables) getApplication()).getTcNo())
                            .setValue(abc);

                    SharedPreferences sr=getSharedPreferences("saglik",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sr.edit();
                    editor.putString("strSaglik",strSaglik);
                    editor.commit();

                    Toast.makeText(CreateProfile.this, "Kayıt Başarılı! Giriş Yapınız", Toast.LENGTH_SHORT).show();
                    Intent intentIleri1 = new Intent(CreateProfile.this, Login.class);
                    startActivity(intentIleri1);
                }else{
                    Toast.makeText(CreateProfile.this, "Sağlık bilgisi alanı doldurulmalıdır.Sağlık probleminiz yoksa (SAĞLIKLI) yazabilirsiniz.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Geri butonu
        geri5_btn = findViewById(R.id.geri5_btn);
        geri5_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGeri1 = new Intent(CreateProfile.this, SignupMailPhone.class);
                startActivity(intentGeri1);
            }
        });

        denemegroup = findViewById(R.id.radiodeneme);
        otherlike= findViewById(R.id.ilgidiger);
        other = findViewById(R.id.ilgi27);

        //Diğer butonu için açılan panel
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otherlike.getVisibility()==View.VISIBLE){
                    otherlike.setVisibility(View.GONE);
                    denemegroup.clearCheck();
                }
                else{
                    otherlike.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}