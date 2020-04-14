package com.multistar.callbeer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.multistar.callbeer.model.Bebida;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import org.w3c.dom.Text;

import java.util.List;

public class Pedido extends AppCompatActivity {
    String tipo;
    RecyclerView mListaBebidas;
    GroupAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        TextView mTxtViewTipoBebida = findViewById(R.id.txtViewTipoBebida);
        adapter = new GroupAdapter();
        mListaBebidas = findViewById(R.id.ListaPedidos);
        mListaBebidas.setAdapter(adapter);
        mListaBebidas.setLayoutManager(new LinearLayoutManager(this));
        if (getIntent().hasExtra("TIPO")){
            Bundle extras = getIntent().getExtras();
            tipo = extras.getString("TIPO");
            mTxtViewTipoBebida.setText(tipo);
        }

    }

    public void buscarBebidas(String tipo){

        FirebaseFirestore.getInstance().collection("/bebidas/"+tipo)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null){
                            Log.e("teste", e.getMessage());
                        }
                        List<DocumentSnapshot> bebidas = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot b: bebidas) {
                            Bebida bebida = b.toObject(Bebida.class);
                            adapter.add(new ItemBebida(bebida));
                        }

                    }
                });

    }

    private class ItemBebida extends Item<ViewHolder>{

        int qtde;
        private Bebida b;

        public ItemBebida(Bebida bebida){
            this.b = bebida;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {

            CheckBox checkBox = viewHolder.itemView.findViewById(R.id.checkBox);
            TextView txtQtde = viewHolder.itemView.findViewById(R.id.txtQtde);

            checkBox.setText("");


        }

        public int getQtde() {
            return qtde;
        }

        public void setQtde(int qtde) {
            this.qtde = qtde;
        }

        public Bebida getB() {
            return b;
        }

        public void setB(Bebida b) {
            this.b = b;
        }

        @Override
        public int getLayout() {
            return R.layout.item_lista;
        }
    }

}