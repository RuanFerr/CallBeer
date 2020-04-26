package com.multistar.callbeer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.multistar.callbeer.Firebase.InsertData;

import java.io.IOException;

//este recurso está sendo usado para teste da ferramenta. no fim dos testes, vao ser feitos ajustes.
public class Reclamacao extends AppCompatActivity {

    private EditText mTxtNomeCliente;
    private EditText mTxtMotivo;
    private EditText mTxtData;
    private EditText mTxtDescricao;
    private Button mBtnAddImg;
    private  Button mbtnEnviarReclamacao;
    private ImageView mImgReclamacao;
    Uri mImagemSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamacao);

        mTxtNomeCliente = findViewById(R.id.txtNomeCliente);
        mTxtMotivo = findViewById(R.id.txtMotivoReclamacao);
        mTxtData = findViewById(R.id.txtDataReclamacao);
        mTxtDescricao = findViewById(R.id.txtDescricaoReclamacao);
        mImgReclamacao = findViewById(R.id.imgReclamacao);

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

        if (data != null) {

            if (requestCode == 0) {

                mImagemSelecionada = data.getData();

                Bitmap bitmap = null;

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImagemSelecionada);
                    mImgReclamacao.setImageDrawable(new BitmapDrawable(bitmap));
                    mBtnAddImg.setAlpha(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void selecionarFoto(){

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);

    }

    public void enviarReclamacao() {
        if (testarCampos()){
            com.multistar.callbeer.model.Reclamacao reclamacao = new com.multistar.callbeer.model.Reclamacao(
                    FirebaseAuth.getInstance().getCurrentUser().getUid().toString(),
                    mTxtNomeCliente.getText().toString(),
                    mTxtMotivo.getText().toString(),
                    mTxtData.getText().toString(),
                    mTxtDescricao.getText().toString()
            );

            com.multistar.callbeer.model.Reclamacao.salvarReclamacao(reclamacao, mImagemSelecionada);


                Toast.makeText(Reclamacao.this, "Enviando", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Reclamacao.this, Principal.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

        } else {
            Toast.makeText(Reclamacao.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean testarCampos() {

        String nomeCliente = mTxtNomeCliente.getText().toString();
        String motivo = mTxtMotivo.getText().toString();
        String data = mTxtData.getText().toString();
        String descricao = mTxtDescricao.getText().toString();



        boolean nomeCli = (nomeCliente.isEmpty() || nomeCliente == null);
        boolean motiv = (motivo.isEmpty() || motivo == null);
        boolean dat = (data.isEmpty() || data == null);
        boolean desc = (descricao.isEmpty() || descricao == null);
        boolean img = (mImagemSelecionada == null);

        return !(nomeCli || motiv || dat || desc || img);
    }

}
