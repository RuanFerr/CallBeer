package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.multistar.callbeer.model.Representante;

public class CadastroPessoa extends AppCompatActivity {

    TextView mCadPessoaNome;
    TextView mCadPessoaEmail;
    TextView mCadPessoaSenha;
    TextView mCadPessoaRota;
    TextView mCadPessoaEndereco;
    TextView mCadPessoaCEP;
    TextView mCadPessoaCPF;
    TextView mCadPessoaRG;
    TextView mCadPessoaTelefone;
    Button mCadPessoaCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        mCadPessoaNome = findViewById(R.id.cadPessoaNome);
        mCadPessoaEmail = findViewById(R.id.cadPessoaEmail);
        mCadPessoaSenha = findViewById(R.id.cadPessoaSenha);
        mCadPessoaRota = findViewById(R.id.cadPessoaRota);
        mCadPessoaEndereco = findViewById(R.id.cadPessoaEndereco);
        mCadPessoaCEP = findViewById(R.id.cadPessoaCEP);
        mCadPessoaCPF = findViewById(R.id.cadPessoaCPF);
        mCadPessoaRG = findViewById(R.id.cadPessoaRG);
        mCadPessoaTelefone = findViewById(R.id.cadPessoaTelefone);
        mCadPessoaCadastrar = findViewById(R.id.btnCadastrarPessoa);

        mCadPessoaCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void cadastrarUsuario(){

        String email = mCadPessoaNome.getText().toString();
        String senha = mCadPessoaSenha.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//Testando se o cadastro funcionou
                        Toast msg = Toast.makeText(getApplicationContext(), "FUNCIONOU", Toast.LENGTH_SHORT);
                        msg.show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
//reportando erro se não funcionar
                        Toast msg = Toast.makeText(getApplicationContext(), "NAO FUNCIONOU", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                });


    }
    //Metodo para criar um usuário do firebase. Vai ser preenchido futuramente
    public void createUser(){

        final String nome = mCadPessoaNome.getText().toString();
        final String email = mCadPessoaEmail.getText().toString();
        String senha = mCadPessoaSenha.getText().toString();
        final String rota = mCadPessoaRota.getText().toString();
        final String endereco = mCadPessoaEndereco.getText().toString();
        final String cep = mCadPessoaCEP.getText().toString();
        final String cpf = mCadPessoaCPF.getText().toString();
        final String rg = mCadPessoaRG.getText().toString();
        final String telefone = mCadPessoaTelefone.getText().toString();

        if (testarCampoVazio()) {
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("AuthSucess", task.getResult().getUser().getUid());
                            String uid = FirebaseAuth.getInstance().getUid();
                            Representante rep = new Representante(uid, nome, email,rota, endereco,cep,cpf,rg,telefone);
                            FirebaseFirestore.getInstance().collection(" /users/representantes").add(rep)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.i("testeStore", "CADASTRO OK");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("TesteStore", e.getMessage());
                                }
                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.i("AuthError", e.getMessage());

                    }
                });

    }

    private boolean testarCampoVazio(){

        String nome = mCadPessoaNome.getText().toString();
        String email = mCadPessoaEmail.getText().toString();
        String senha = mCadPessoaSenha.getText().toString();
        String rota = mCadPessoaRota.getText().toString();
        String endereco = mCadPessoaEndereco.getText().toString();
        String cep = mCadPessoaCEP.getText().toString();
        String cpf = mCadPessoaCPF.getText().toString();
        String rg = mCadPessoaRG.getText().toString();
        String telefone = mCadPessoaTelefone.getText().toString();

        boolean errNome = (nome == null || nome.isEmpty());
        boolean errEmail = (email == null || email.isEmpty());
        boolean errSenha = (senha == null || senha.isEmpty());
        boolean errRota = (rota == null || rota.isEmpty());
        boolean errEndereco = (endereco == null || endereco.isEmpty());
        boolean errCep = (cep == null || cep.isEmpty());
        boolean errCpf = (cpf == null || cpf.isEmpty());
        boolean errRg = (rg == null || rg.isEmpty());
        boolean errTelefone = (telefone == null || telefone.isEmpty());

        return (errNome || errEmail || errSenha || errRota || errEndereco || errCep || errCpf || errRg || errTelefone);
    }

}
