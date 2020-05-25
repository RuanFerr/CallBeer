package com.multistar.callbeer;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.multistar.callbeer.model.Bebida;
import com.multistar.callbeer.model.ItemPedido;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;


public class ItemCarrinho extends Item<ViewHolder>{

    private ItemPedido itemPedido;
    private int posicao;
    Button mremover;
    TextView mNomeBebida;
    TextView mQtdeBebida;

    public ItemCarrinho(ItemPedido pedido, int posicao){
        this.itemPedido = pedido;

    }

    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        mNomeBebida = viewHolder.itemView.findViewById(R.id.txtNomeItemCarrinho);
        mQtdeBebida = viewHolder.itemView.findViewById(R.id.txtQtdeItemCarrinho);
        mremover = viewHolder.itemView.findViewById(R.id.btnRemoverItemCarrinho);

        mNomeBebida.setText(itemPedido.getBebida().getDescricao());
        mQtdeBebida.setText(String.valueOf(itemPedido.getQuantidade()));

    }

    @Override
    public int getLayout() {
        return R.layout.item_carrinho;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public Button getMremover() {
        return mremover;
    }

    public void setMremover(Button mremover) {
        this.mremover = mremover;
    }

    public TextView getmNomeBebida() {
        return mNomeBebida;
    }

    public void setmNomeBebida(TextView mNomeBebida) {
        this.mNomeBebida = mNomeBebida;
    }

    public TextView getmQtdeBebida() {
        return mQtdeBebida;
    }

    public void setmQtdeBebida(TextView mQtdeBebida) {
        this.mQtdeBebida = mQtdeBebida;
    }
}
