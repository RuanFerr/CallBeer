package com.multistar.callbeer.model;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Pedido {

    private ArrayList<ItemPedido> itensPedido;
    private String UIDCliente;

    public Pedido(ArrayList<ItemPedido> carrinho){
        this.UIDCliente = FirebaseAuth.getInstance().getCurrentUser().getUid();
        this.itensPedido = carrinho;
    }
    public String getUIDCliente() {
        return UIDCliente;
    }

    public void setUIDCliente(String UIDCliente) {
        this.UIDCliente = UIDCliente;
    }

    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
