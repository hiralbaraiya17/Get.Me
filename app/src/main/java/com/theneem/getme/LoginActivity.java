package com.theneem.getme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {


    TextView txt_register, txt_forgotpass;
    MaterialButton btn_login;

    TextInputEditText edt_login_email, edt_login_password;
    //private LoginButton loginButton;

    PreferenceManager preferenceManager;


    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_register = findViewById(R.id.txt_register);

        edt_login_email = findViewById(R.id.edt_login_email);
        edt_login_password = findViewById(R.id.edt_login_password);




        //---------------------------------------------------------------------------

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                LoginActivity.this.startActivity(intent);

            }
        });

        //----------------------------------------------------------------------------------------------


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
                if (edt_login_email.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (!edt_login_email.getText().toString().matches(emailPattern)) {
                    Toast.makeText(LoginActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else if (edt_login_password.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                } else if (edt_login_password.getText().toString().length() < 6) {
                    Toast.makeText(LoginActivity.this, "Please enter min 6 digit passoword", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "all set to go ", Toast.LENGTH_SHORT).show();

                    //preferenceManager.setLoginSession(true);
                    //login(edt_login_email.getText().toString(), edt_login_password.getText().toString());
                }

            }
        });


    }
}
