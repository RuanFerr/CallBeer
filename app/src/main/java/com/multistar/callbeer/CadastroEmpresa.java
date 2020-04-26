package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.multistar.callbeer.model.Empresa;

public class CadastroEmpresa extends AppCompatActivity {

    EditText mCadEmpresaNome;
    EditText mCadEmpresaRazaoSocial;
    EditText mCadEmpresaEmail;
    EditText mCadEmpresaSenha;
    EditText mCadEmpresaEndereco;
    EditText mCadEmpresaCep;
    EditText mCadEmpresaCnpj;
    EditText mCadEmpresaIe;
    EditText mCadEmpresaTelefone;
    Button mBtnCadastrarEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mCadEmpresaNome = findViewById(R.id.cadEmpresaNome);
        mCadEmpresaRazaoSocial = findViewById(R.id.cadEmpresaRazaoSocial);
        mCadEmpresaEmail = findViewById(R.id.cadEmpresaEmail);
        mCadEmpresaSenha = findViewById(R.id.cadEmpresaSenha);
        mCadEmpresaEndereco = findViewById(R.id.cadEmpresaEndereco);
        mCadEmpresaCep = findViewById(R.id.cadEmpresaCEP);
        mCadEmpresaCnpj = findViewById(R.id.cadEmpresaCNPJ);
        mCadEmpresaIe = findViewById(R.id.cadEmpresaIE);
        mCadEmpresaTelefone = findViewById(R.id.cadEmpresaTelefone);
        mBtnCadastrarEmpresa = findViewById(R.id.btnCadastrarEmpresa);

        mBtnCadastrarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarEmpresa();
            }
        });

    }

    public void cadastrarEmpresa(){

        String email = mCadEmpresaNome.getText().toString();
        String senha = mCadEmpresaSenha.getText().toString();
        final Empresa empresa = new Empresa(mCadEmpresaNome.getText().toString(), mCadEmpresaRazaoSocial.getText().toString(), mCadEmpresaEmail.getText().toString(), mCadEmpresaEndereco.getText().toString(), Integer.valueOf(mCadEmpresaCep.getText().toString()), Integer.valueOf(mCadEmpresaCnpj.getText().toString()), mCadEmpresaIe.getText().toString(), mCadEmpresaTelefone.getText().toString());

        if (testarCampos()) {

            Toast.makeText(CadastroEmpresa.this, "Preencha corretamente todos os campos", Toast.LENGTH_SHORT).show();

            Log.i("TESTE_CAD", "LOGIN_ERR");
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseFirestore.getInstance()
                                .collection("users").document().collection("empresas")
                                .add(empresa)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.i("teste", "OK");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("teste", e.getMessage());
                                    }
                                });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("auth", e.getMessage());
                    }
                });


    }

    public boolean testarCampos(){

        boolean a = (

                (mCadEmpresaEmail.getText().toString().isEmpty() || mCadEmpresaEmail.getText().toString() == null) ||
                        (mCadEmpresaNome.getText().toString().isEmpty() || mCadEmpresaNome.getText().toString() == null) ||
                        (mCadEmpresaEndereco.getText().toString().isEmpty() || mCadEmpresaEndereco.getText().toString() == null) ||
                        (mCadEmpresaIe.getText().toString().isEmpty() || mCadEmpresaIe.getText().toString() == null) ||
                        (mCadEmpresaRazaoSocial.getText().toString().isEmpty() || mCadEmpresaRazaoSocial.getText().toString() == null) ||
                        (mCadEmpresaTelefone.getText().toString().isEmpty() || mCadEmpresaTelefone.getText().toString() == null) ||
                        (mCadEmpresaCep.getText().toString().isEmpty() || mCadEmpresaCep.getText().toString() == null) ||
                        (mCadEmpresaCnpj.getText().toString().isEmpty() || mCadEmpresaCnpj.getText().toString() == null) ||
                        (mCadEmpresaSenha.getText().toString().isEmpty() || mCadEmpresaSenha.getText().toString() == null)

        );

        return a;

    }

}
