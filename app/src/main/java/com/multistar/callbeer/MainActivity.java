package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mEditEmail = findViewById(R.id.editEmailLogin);
        final EditText mEditSenha = findViewById(R.id.editSenhaLogin);
        final Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString();
                String senha = mEditSenha.getText().toString();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               Log.i("testeFailure", "Falha encontrada");
                            }
                        });

                Log.i("teste", email);
                Log.i("teste", senha);
            }
        });
    }

    public void activityCad(){

        Intent intent = new Intent(this, CadastroEmpresa.class);
        startActivity(intent);

    };

}
