package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

                //testando se os campos estão vazios
                if ((email == null || email.isEmpty()) || (senha == null || senha.isEmpty())) {
                    Toast.makeText(getApplicationContext(), "Preencha corretamente os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    Intent intent = new Intent(MainActivity.this, Principal.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);

                                }

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

    //criando intent pra ir pra tela de cadastro. essa atividade será acessado apenas por um tipo de usuário após os testes
    /**public void testingCadCLI() {
     Intent intent = new Intent(this, CadastroPessoa.class);

     startActivity(intent);
     }**/

}
