package com.multistar.callbeer.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.multistar.callbeer.model.Empresa;
import com.multistar.callbeer.model.Representante;

public class InsertData {


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

}
