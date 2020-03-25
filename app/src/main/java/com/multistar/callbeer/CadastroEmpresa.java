package com.multistar.callbeer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

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
    }
}
