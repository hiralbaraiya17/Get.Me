package com.theneem.getme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {

    TextView txt_login;
    MaterialButton btn_register;
    TextInputEditText edt_userName, edt_email, edt_password, edt_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(this).setTheme(R.style.OPAppTheme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        btn_register = findViewById(R.id.btn_register);


        edt_userName = findViewById(R.id.edt_userName);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_mobile = findViewById(R.id.edt_mobile);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

                if (edt_userName.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please add UserName", Toast.LENGTH_SHORT).show();
                } else if (edt_email.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please add Email Id", Toast.LENGTH_SHORT).show();
                } else if (!edt_email.getText().toString().matches(emailPattern)) {
                    Toast.makeText(RegisterActivity.this, "Please enter valid email Id", Toast.LENGTH_SHORT).show();
                } else if (edt_password.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please add Password ", Toast.LENGTH_SHORT).show();
                } else if (edt_password.getText().toString().length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Please enter min 6 digit passoword ", Toast.LENGTH_SHORT).show();
                } else if (edt_mobile.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please add Mobile Number", Toast.LENGTH_SHORT).show();
                } else if (edt_mobile.getText().toString().length() != 10) {
                    Toast.makeText(RegisterActivity.this, "Please enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "great to go ", Toast.LENGTH_SHORT).show();

                    //preferenceManager.setLoginSession(true);
                    //retrofit(edt_userName.getText().toString(), edt_email.getText().toString(), edt_password.getText().toString(), edt_mobile.getText().toString());
                }
            }
        });

        txt_login = findViewById(R.id.txt_login);

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                RegisterActivity.this.startActivity(intent);
            }
        });

    }
}
