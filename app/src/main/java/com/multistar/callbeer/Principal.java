package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.multistar.callbeer.model.Bebida;

public class Principal extends AppCompatActivity {

    private Button mBtCervejas;
    private Button mBtChopes;
    private Button mBtEnergeticos;
    private Button mBtMista;
    private Button mBtRefrigerantes;
    private Button mBtSucos;
    private Button mBtIsotonicos;
    private Button mBtAguasEChas;
    private ImageView mImgPrincipal;

    private Button testeRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        verifyAuth();

        mBtCervejas = findViewById(R.id.btCervejas);
        mBtChopes = findViewById(R.id.btChopes);
        mBtEnergeticos = findViewById(R.id.btEnergeticos);
        mBtMista = findViewById(R.id.btMista);
        mBtRefrigerantes = findViewById(R.id.btRefrigerantes);
        mBtSucos = findViewById(R.id.btSucos);
        mBtIsotonicos = findViewById(R.id.btIsotonicos);
        mBtAguasEChas = findViewById(R.id.btAguasEChas);
        mImgPrincipal = findViewById(R.id.imageView2);

        mBtCervejas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("cervejas");
            }
        });

        mBtChopes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("chopes");
            }
        });

        mBtEnergeticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("energeticos");
            }
        });

        mBtMista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("mista");
            }
        });

        mBtRefrigerantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("refrigerantes");
            }
        });

        mBtSucos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("sucos");
            }
        });

        mBtIsotonicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("isotonicos");
            }
        });

        mBtAguasEChas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPedido("aguasEChas");
            }
        });

        testeRec = findViewById(R.id.btnRec);
        testeRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Reclamacao.class);
                startActivity(intent);
            }
        });
    }

    private void verifyAuth(){
        if (FirebaseAuth.getInstance().getUid() == null) {

            Intent intent = new Intent(Principal.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void abrirPedido(String tipo){
        Intent intent = new Intent(Principal.this, Pedido.class);
        intent.putExtra("TIPO", tipo);

        startActivity(intent);
    }

}
