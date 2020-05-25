package com.multistar.callbeer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Central extends AppCompatActivity {

    Button mBtnConfirmar;
    Button mBtnChat;
    Button mBtnPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);

        verifyAuth();

        mBtnConfirmar = findViewById(R.id.btnConfirmar);
        mBtnChat = findViewById(R.id.btnChat);
        mBtnPedidos = findViewById(R.id.btnPedidos);

        mBtnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Central.this, Pedidos.class);
                startActivity(intent);
            }
        });

        mBtnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Central.this, Carrinho.class);
                startActivity(intent);
            }
        });

    }
    private void verifyAuth(){
        if (FirebaseAuth.getInstance().getUid() == null) {

            Intent intent = new Intent(Central.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
