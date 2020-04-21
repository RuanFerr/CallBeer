package com.multistar.callbeer.Firebase;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.multistar.callbeer.model.Empresa;
import com.multistar.callbeer.model.Reclamacao;
import com.multistar.callbeer.model.Representante;

public class InsertData {
    private static boolean retorno = false;
    public InsertData(){
        retorno = false;
    }

    public static boolean isRetorno() {
        return retorno;
    }

    public static void setRetorno(boolean retorno) {
        InsertData.retorno = retorno;
    }

    //metodo para inserir um representante
    public void create(Representante representante){

        FirebaseFirestore.getInstance().collection("representantes")
                .add(representante)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("teste", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("teste", e.getMessage());
                    }
                });

    }


    //Overload do metodo create para inserir empresa
    public void create(Empresa empresa){

        FirebaseFirestore.getInstance().collection("empresas")
                .add(empresa)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("teste", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("teste", e.getMessage());
                    }
                });

    }

    //Overload do metodo create para inserir Reclamação
    public void create(Reclamacao reclamacao){

        FirebaseFirestore.getInstance().collection("reclamacao")
                .add(reclamacao)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i("testeReclamacao", documentReference.getId());
                        retorno = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("testeReclamacao", e.getMessage());
                        retorno = false;
                    }
                });
    }

}
