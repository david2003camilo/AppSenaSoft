package com.sena.quehaypahacer.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sena.quehaypahacer.R;
import com.sena.quehaypahacer.ui.login.Login;

public class SplashApp extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ThemeNotBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashApp.this, Login.class );
                startActivity(intent);
                finish();
            }
        },2000);
    }
}