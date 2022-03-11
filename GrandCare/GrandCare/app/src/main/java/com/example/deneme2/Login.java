package com.example.deneme2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {
    Button register;
    ImageView login, googleLogin;
    EditText editUserName, editPassword;
    String txtUserName, txtPassword;
    private String getUserName, getPassword;
    private DatabaseReference dr;
    private GoogleSignInClient googleSignInClient;
    private SwitchCompat rememberMeSwitch;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String PREFS_NAME = "PrefsFile";
    private static final int lg = 9001;

    private DataSnapshot s;

    private void getPreferencesData() {
        preferences = this.getSharedPreferences("com.example.deneme2", Context.MODE_PRIVATE);
        getUserName = preferences.getString("username", null);
        getPassword = preferences.getString("password", null);
        if (!TextUtils.isEmpty(getUserName)){
            editUserName.setText(getUserName);
            editPassword.setText(getPassword);}

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUserName = (EditText) findViewById(R.id.giris_kullanıcı_ad);        //item id ile belirli türe ait nesne çağrılır.
        editPassword = (EditText) findViewById(R.id.giris_sifre);
        txtUserName = editUserName.getText().toString();
        txtPassword = editPassword.getText().toString();
        rememberMeSwitch = (SwitchCompat) findViewById(R.id.rememberMeSwitch);

        //Beni hatırla için varsa kayıtlı bilgileri çeker
        getPreferencesData();

        dr = FirebaseDatabase.getInstance().getReference().child("Users").child("Tc");

        //Kayıt butonu
        register = findViewById(R.id.registerbtn);
        register.setOnClickListener(new View.OnClickListener() {                                            //
            @Override
            //
            public void onClick(View v) {                                                                   //
                Intent intentkayit = new Intent(Login.this, SignupName.class);                 //
                startActivity(intentkayit);                                                                 //
            }                                                                                               //
        });                                                                                                 //

        //Login butonu
        login = findViewById(R.id.loginclickimage);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtUserName = editUserName.getText().toString();
                txtPassword = editPassword.getText().toString();

                if (txtUserName.isEmpty()) {

                    Toast.makeText(Login.this, "Kullanıcı Adınızı Giriniz!", Toast.LENGTH_SHORT).show();

                }
                if (txtPassword.isEmpty()) {
                    Toast.makeText(Login.this, "Şifrenizi Giriniz", Toast.LENGTH_SHORT).show();
                }

                loginFunction(txtUserName, txtPassword);

            }

        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso); //Google isteği atıyoruz
        googleLogin = findViewById(R.id.googleView);
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                launcher.launch(signInIntent);
            }
        });
    }

    //Giriş Fonksiyonu
    private void loginFunction(String txtUserName, String txtPassword) {

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String kullaniciAdi="";
                String sifre="";

                for(DataSnapshot s:snapshot.getChildren())
                {
                    kullaniciAdi=s.child("userName").getValue(String.class);
                    sifre=s.child("password").getValue(String.class);

                    if(txtUserName.equals(kullaniciAdi) && txtPassword.equals(sifre))
                    {
                        //Beni hatırla aktifse
                        if(rememberMeSwitch.isChecked()){
                            editor = preferences.edit();
                            editor.putString("username", txtUserName);
                            editor.putString("password", txtPassword);
                            editor.apply();
                        }else{
                            editor = preferences.edit();
                            editor.putString("username", null);
                            editor.putString("password", null);
                            editor.apply();
                        }
                        takeUser(s);
                        startActivity(new Intent(Login.this, MainPage.class));
                        Toast.makeText(Login.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(Login.this,"Giriş başarısız ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Google Hesabı ile giriş
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Task<GoogleSignInAccount> account = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount hesap = account.getResult(ApiException.class);
                    Log.d("GoogleActivity", "Google Girişi Başarılı" + hesap.getId());
                    assert hesap !=null;
                } catch (ApiException e) {
                    Log.w("GoogleActivity", "Google girişi başarısız oldu");
                }
            }
        }
    });

    //Kullanıcı bilgilerini global değişkenlere atan metod
    public void takeUser(DataSnapshot s){
        this.s = s;
        String ad, soyad, dogumtarihi, sehir, tcNo, mail, telNo,kullaniciAdi,sifre,saglik;

        ad=s.child("name").getValue(String.class);
        soyad=s.child("lastName").getValue(String.class);
        dogumtarihi=s.child("birthday").getValue(String.class);
        sehir=s.child("city").getValue(String.class);
        tcNo=s.child("tcNo").getValue(String.class);
        mail=s.child("mail").getValue(String.class);
        telNo=s.child("telNo").getValue(String.class);
        kullaniciAdi=s.child("userName").getValue(String.class);
        sifre=s.child("password").getValue(String.class);
        saglik=s.child("saglik").getValue(String.class);

        ((GlobalVariables) getApplication()).setAd(ad);
        ((GlobalVariables) getApplication()).setSoyad(soyad);
        ((GlobalVariables) getApplication()).setDogumTarihi(dogumtarihi);
        ((GlobalVariables) getApplication()).setSehir(sehir);
        ((GlobalVariables) getApplication()).setUserName(kullaniciAdi);
        ((GlobalVariables) getApplication()).setPassword(sifre);
        ((GlobalVariables) getApplication()).setTcNo(tcNo);
        ((GlobalVariables) getApplication()).setMail(mail);
        ((GlobalVariables) getApplication()).setTelNo(telNo);
        ((GlobalVariables) getApplication()).setSaglik(saglik);
    }
}