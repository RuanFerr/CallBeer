package com.multistar.callbeer.model;

public class ItemPedido {
    private String id;
    private String UIDCliente;
    private Bebida bebida;
    private int quantidade;

    public String getUIDCliente() {
        return UIDCliente;
    }

    public void setUIDCliente(String UIDCliente) {
        this.UIDCliente = UIDCliente;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
