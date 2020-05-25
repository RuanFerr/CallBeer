package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.multistar.callbeer.model.Bebida;
import com.multistar.callbeer.model.ItemPedido;
import com.multistar.callbeer.model.Pedido;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;

public class Carrinho extends AppCompatActivity {

    public static ArrayList<ItemBebida> bebidas = new ArrayList<>();
    public static ArrayList<ItemCarrinho> itensCarrinho = new ArrayList<>();

    RecyclerView mCarrinho;

    RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

    GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);



        adapter = new GroupAdapter();

        mCarrinho = findViewById(R.id.recyclerCarrinho);

        mCarrinho.setAdapter(adapter);
        mCarrinho.setLayoutManager(lm);
        buscarCarrinho();
    }

    void buscarCarrinho(){
        for (int i = 0; i < itensCarrinho.size(); i++) {

            adapter.add(itensCarrinho.get(i));

        }
        adapter.add(new BtnConfirmar()); //botao
        adapter.notifyDataSetChanged();
    }

    private class BtnConfirmar extends Item<ViewHolder> {

        private Button mBtnConfirmar;

        public BtnConfirmar(){

        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {

            mBtnConfirmar = viewHolder.itemView.findViewById(R.id.btnAdicionar);
            mBtnConfirmar.setText("Confirmar Pedido");
            mBtnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    confirmarPedido();

                }
            });

        }

        @Override
        public int getLayout() {
            return R.layout.btn_adicionar;
        }
        public Button getmBtnConfirmar() {
            return mBtnConfirmar;
        }
        public void setmBtnConfirmar(Button mBtnConfirmar) {
            this.mBtnConfirmar = mBtnConfirmar;
        }
    }

    private void confirmarPedido() {

        ArrayList<ItemPedido> itens = new ArrayList<>();

        for(int i = 0; i< itensCarrinho.size(); i++) {

            itens.add(itensCarrinho.get(i).getItemPedido());

        }

        Pedido pedido = new Pedido(itens);

        CollectionReference ref = FirebaseFirestore.getInstance().collection("pedidos");
        ref.add(pedido);
    }

}
