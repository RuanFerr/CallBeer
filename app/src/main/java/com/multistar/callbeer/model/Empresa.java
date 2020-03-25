package com.multistar.callbeer.model;

public class Empresa {

private String nome;
private String razaoSocial;
private String email;
private String endereco;
private int CEP;
private int cnpj;
private String ie;
private String telefone;

    public Empresa(String nome, String razaoSocial, String email, String endereco, int CEP, int cnpj, String ie, String telefone) {
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.endereco = endereco;
        this.CEP = CEP;
        this.cnpj = cnpj;
        this.ie = ie;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
