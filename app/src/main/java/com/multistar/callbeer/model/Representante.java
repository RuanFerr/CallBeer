package com.multistar.callbeer.model;

public class Representante {

    private String uid;
    private String nome;
    private String email;
    private String rota;
    private String endereco;
    private String cep;
    private String cpf;
    private String rg;
    private String telefone;

    public Representante(){

    }

    public Representante(String uid, String nome, String email, String rota, String endereco, String cep, String cpf, String rg, String telefone){

        this.uid = uid;
        this.nome = nome;
        this.email = email;
        this.rota = rota;
        this.endereco = endereco;
        this.cep = cep;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
