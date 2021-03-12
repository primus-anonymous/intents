package com.example.lesson5.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lesson5.R;
import com.example.lesson5.domain.Account;
import com.example.lesson5.domain.AuthProvider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int LOGIN_REQUEST = 3;
    private final AuthProvider authProvider = AuthProvider.INSTANCE;
    private Button btnAuth;
    private TextView loggedIn;
    private TextView needAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String value = getIntent().getStringExtra("PARAM");

        btnAuth = findViewById(R.id.btn_auth);
        loggedIn = findViewById(R.id.logged_in_message);
        needAuth = findViewById(R.id.message_need_auth);

        if (value != null) {
            needAuth.setText(value);
        }

        updateView();

        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra(LoginActivity.TOKEN_KEY, "token");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, LOGIN_REQUEST);
            }
        });

        findViewById(R.id.btn_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://google.com");
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Account account = data.getParcelableExtra(LoginActivity.ACCOUNT_KEY);
                }
                updateView();
            }
        }
    }

    private void updateView() {
        if (authProvider.isAuthorized()) {
            loggedIn.setVisibility(View.VISIBLE);
            needAuth.setVisibility(View.GONE);
            btnAuth.setVisibility(View.GONE);
        } else {
            loggedIn.setVisibility(View.GONE);
            needAuth.setVisibility(View.VISIBLE);
            btnAuth.setVisibility(View.VISIBLE);
        }

    }
}