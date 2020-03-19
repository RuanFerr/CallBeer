package com.multistar.callbeer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText mEditEmail = findViewById(R.id.editEmailLogin);
        final EditText mEditSenha = findViewById(R.id.editSenhaLogin);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString();
                String senha = mEditSenha.getText().toString();
                Log.i("teste", email);
                Log.i("teste", senha);
            }
        });
    }
}
