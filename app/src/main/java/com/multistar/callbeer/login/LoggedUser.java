package com.multistar.callbeer.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedUser {

    private String email;
    private String id;
    private String nome;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void createUserInFirebase(String email, String senha){

      FirebaseAuth auth = FirebaseAuth.getInstance();

      FirebaseUser user = auth.getCurrentUser();

      user.updateEmail(email);

    user.sendEmailVerification();

    }

}
