package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.multistar.callbeer.model.Bebida;
import com.multistar.callbeer.model.ItemPedido;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class Pedido extends AppCompatActivity {
    String tipo;
    ArrayList<Integer> qttsCerveja = new ArrayList<>();
    RecyclerView mListaBebidas;

    static ArrayList<ItemBebida> itens = new ArrayList<>();

    ArrayList<ItemBebida> bbd = new ArrayList<>();

    RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

    GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        TextView mTxtViewTipoBebida = findViewById(R.id.txtViewTipoBebida);
        adapter = new GroupAdapter();

        itens = new ArrayList<>();

        mListaBebidas = findViewById(R.id.ListaPedidos);
        mListaBebidas.setAdapter(adapter);

        mListaBebidas.setLayoutManager(lm);
        if (getIntent().hasExtra("TIPO")){
            Bundle extras = getIntent().getExtras();
            tipo = extras.getString("TIPO");
            mTxtViewTipoBebida.setText(tipo);
            this.tipo = tipo;

        }
        buscarBebidas(tipo);
    }
    public void buscarBebidas(final String tipoBebida){

        FirebaseFirestore.getInstance().collection("/bebidas")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null){
                            Log.e("testeErro", e.getMessage());

                        }
                        List<DocumentSnapshot> bebidas = queryDocumentSnapshots.getDocuments();
                        int ctrl = 0;
                        ViewHolder vh = new ViewHolder(mListaBebidas);

                        for (DocumentSnapshot b : bebidas) {

                            Bebida bebida = b.toObject(Bebida.class);
                            if (bebida.getTipo().equals(tipoBebida)) {

                                // bbd.add(new ItemBebida(bebida));
                                //ctrl = bbd.size()-1;

                                itens.add(new ItemBebida(bebida, ctrl));

                                adapter.add(itens.get(ctrl));
                                Log.i("tttt", String.valueOf(ctrl));
                                ctrl = ctrl + 1;
                            }
                        }
                        adapter.add(new BtnPedido());
                        adapter.notifyDataSetChanged();
                    }
                });

    }


    private class BtnPedido extends Item<ViewHolder>{

        private Button mBtnPedido;

        public BtnPedido(){

        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {

            mBtnPedido = viewHolder.itemView.findViewById(R.id.btnAdicionar);

            mBtnPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    adicionarBebidas();

                }
            });

        }

        @Override
        public int getLayout() {
            return R.layout.btn_adicionar;
        }
        public Button getmBtnPedido() {
            return mBtnPedido;
        }
        public void setmBtnPedido(Button mBtnPedido) {
            this.mBtnPedido = mBtnPedido;
        }
    }

    public void adicionarBebidas(){

        for (int i = 0; i < itens.size(); i ++){
            if (itens.get(i).isCheck()) {

                Carrinho.bebidas.add(itens.get(i));

                ItemPedido item = new ItemPedido(itens.get(i).getB(), itens.get(i).getQtde());

                CollectionReference ref = FirebaseFirestore.getInstance().collection("pedido");
                ref.add(item);

            }
        }

        /*for (int i = 0; i < ItemBebida.checkBoxex.size(); i++){
            int c = 0;
            if (ItemBebida.checkBoxex.get(i) == true) {

                ItemBebida ib = (ItemBebida) adapter.getItem(i);

                Carrinho.bebidas.add(ib);
                Carrinho.bebidas.get(c).setQtde(Integer.parseInt(ItemBebida.Qtts.get(i)));
                c = c + 1;
            }
        }*/

        Intent intent = new Intent(Pedido.this, Principal.class);
        Toast.makeText(Pedido.this, "Adicionando", Toast.LENGTH_SHORT).show();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}
