package com.multistar.callbeer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.multistar.callbeer.Firebase.CadEmpresa;
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

    public void CadastrarEmpresa(){

        CadEmpresa cad = new CadEmpresa();

        Empresa empresa = new Empresa(mCadEmpresaNome.getText().toString(), mCadEmpresaRazaoSocial.getText().toString(), mCadEmpresaEmail.getText().toString(), mCadEmpresaEndereco.getText().toString(), Integer.valueOf(mCadEmpresaCep.getText().toString()), Integer.valueOf(mCadEmpresaCnpj.getText().toString()), mCadEmpresaIe.getText().toString(), mCadEmpresaTelefone.getText().toString());

        if (testarCampos(empresa)) {
            Log.i("TESTE_CAD", "LOGIN_ERR");
            return;
        }

    }

   public boolean testarCampos(Empresa empresa){

        boolean a = (
                (empresa.getEmail() == "" || empresa.getEmail().isEmpty()) ||
                        (empresa.getNome() == "" || empresa.getNome().isEmpty() ) ||
                        (empresa.getEndereco() == "" || empresa.getEndereco().isEmpty()) ||
                        (empresa.getIe() == "" || empresa.getIe().isEmpty()) ||
                        (empresa.getRazaoSocial() == "" || empresa.getIe().isEmpty()) ||
                        (empresa.getTelefone() == "" || empresa.getTelefone().isEmpty()) ||
                        ("".equals(empresa.getCEP()) || empresa.getCEP() == 0) ||
                        ("".equals(empresa.getCnpj()) || empresa.getCnpj() == 0)

        );

        return !a;

   }

}
