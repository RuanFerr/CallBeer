package com.multistar.callbeer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.multistar.callbeer.Firebase.InsertData;

//este recurso está sendo usado para teste da ferramenta. no fim dos testes, vao ser feitos ajustes.
public class Reclamacao extends AppCompatActivity {

    private EditText mTxtNomeCliente;
    private EditText mTxtMotivo;
    private EditText mTxtData;
    private EditText mTxtDescricao;
    private Button mBtnAddImg;
    private  Button mbtnEnviarReclamacao;
    Uri mImagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao);

        mTxtNomeCliente = findViewById(R.id.txtNomeCliente);
        mTxtMotivo = findViewById(R.id.txtMotivoReclamacao);
        mTxtData = findViewById(R.id.txtDataReclamacao);
        mTxtDescricao = findViewById(R.id.txtDescricaoReclamacao);

        mBtnAddImg = findViewById(R.id.btnAddImgReclamacao);
        mbtnEnviarReclamacao = findViewById(R.id.btnEnviarReclamacao);

        mBtnAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarFoto();
            }
        });

        mbtnEnviarReclamacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarReclamacao();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {

            mImagemSelecionada = data.getData();

        }
    }

    public void selecionarFoto(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);

    }

    public void enviarReclamacao(){

        com.multistar.callbeer.model.Reclamacao reclamacao = new com.multistar.callbeer.model.Reclamacao(
                FirebaseAuth.getInstance().getCurrentUser().getUid().toString(),
                mTxtNomeCliente.getText().toString(),
                mTxtMotivo.getText().toString(),
                mTxtData.getText().toString(),
                mTxtDescricao.getText().toString()
        );

        com.multistar.callbeer.model.Reclamacao.salvarReclamacao(reclamacao, mImagemSelecionada);

        if (InsertData.isRetorno() == true) {
            Toast.makeText(Reclamacao.this, "Enviado com sucesso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Reclamacao.this, Principal.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(Reclamacao.this, "Erro ao enviar", Toast.LENGTH_SHORT).show();
        }

    }

}
