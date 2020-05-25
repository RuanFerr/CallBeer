package com.multistar.callbeer.model;

import com.google.firebase.auth.FirebaseAuth;

public class ItemPedido {
    private Bebida bebida;
    private int quantidade;
    private String estado;  //"ESPERA" para pedido esperando confirmação, "CONFIRMADO" para pedido confirmado e esperando entrega
                            //"ENTREGUE" para pedido entregue, "CANCELADO" para pedido cancelado

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ItemPedido(Bebida bebida, int quantidade){
        this.bebida = bebida;
        this.quantidade = quantidade;
        this.estado = "ESPERA";
    }

    public ItemPedido(){};

}
