package com.multistar.callbeer;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.multistar.callbeer.model.Bebida;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;

public class ItemBebida extends Item<ViewHolder> {

    public static ArrayList<String> Qtts = new ArrayList<>();
    public static ArrayList<Boolean> checkBoxex = new ArrayList<>();

    private int qtde;
    private Bebida b;
    private CheckBox checkBox;
    private TextView txtQtde;
    private int posicao;
    public ItemBebida(Bebida bebida, int posicao){

        this.b = bebida;
        this.posicao = posicao;

    }
    @Override
    public void bind(@NonNull ViewHolder viewHolder, int position) {
        checkBox = viewHolder.itemView.findViewById(R.id.checkBox);
        TextView txtQtde = viewHolder.itemView.findViewById(R.id.txtQtde);
        checkBox.setText(b.getDescricao());

        txtQtde.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                Qtts.set(posicao, v.getText().toString());

                Log.i("tttt", Qtts.get(posicao));
                return false;
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (checkBox.isChecked()) {

                    ItemBebida.checkBoxex.set(posicao, true);

                } else {
                    ItemBebida.checkBoxex.set(posicao, false);
                }
                Log.i("tttt", checkBoxex.get(posicao).toString());

            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.item_lista;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public TextView getTxtQtde() {
        return txtQtde;
    }

    public void setTxtQtde(TextView txtQtde) {
        this.txtQtde = txtQtde;
    }
}