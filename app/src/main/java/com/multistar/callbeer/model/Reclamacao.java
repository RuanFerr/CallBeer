package com.multistar.callbeer.model;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.multistar.callbeer.Firebase.InsertData;

import java.util.UUID;

public class Reclamacao {

    private String idUserReclamacao;
    private String cliente;
    private String motivo;
    private String data;
    private String descricao;
    private String imagemReclamacao;

    public Reclamacao(String idUser, String cliente, String motivo, String data, String descricao){
        this.idUserReclamacao = idUser;
        this.cliente = cliente;
        this.motivo = motivo;
        this.data = data;
        this.descricao = descricao;
    }

    public String getIdUserReclamacao() {
        return idUserReclamacao;
    }

    public void setIdUserReclamacao(String idUserReclamacao) {
        this.idUserReclamacao = idUserReclamacao;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemReclamacao() {
        return imagemReclamacao;
    }

    public void setImagemReclamacao(String imagemReclamacao) {
        this.imagemReclamacao = imagemReclamacao;
    }

    public static void salvarReclamacao(Reclamacao reclamacao, Uri img) {

        String nomeImagem = UUID.randomUUID().toString();
        final Reclamacao rec = reclamacao;

        final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/" + nomeImagem);
        ref.putFile(img)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("testeReclamacao", uri.toString());

                                rec.setImagemReclamacao(uri.toString());

                                InsertData data = new InsertData();

                              data.create(rec);

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("testeReclamacao", e.getMessage());
                    }
                });

    }
}
