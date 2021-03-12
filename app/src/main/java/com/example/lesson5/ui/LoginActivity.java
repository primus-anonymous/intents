package com.example.lesson5.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lesson5.R;
import com.example.lesson5.domain.Account;
import com.example.lesson5.domain.AuthProvider;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String TOKEN_KEY = "TOKEN_KEY";
    public static final String ACCOUNT_KEY = "ACCOUNT_KEY";
    private final AuthProvider authProvider = AuthProvider.INSTANCE;

    private String tmpToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String token = getIntent().getStringExtra(TOKEN_KEY);

        findViewById(R.id.btn_auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Account account = new Account();
                account.setInd("id1");
                account.setName("Name");

                authProvider.setAuthorized(true);

                Intent intent = new Intent();
                intent.putExtra(ACCOUNT_KEY, account);

                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
    }

    public String getTmpToken() {
        return tmpToken;
    }

    public void setTmpToken(String tmpToken) {
        this.tmpToken = tmpToken;
    }
}