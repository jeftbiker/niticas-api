package com.sena.adsi.noticiasapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sena.adsi.noticiasapp.Adaptador.Adapter;
import com.sena.adsi.noticiasapp.Modelo.Detalle;
import com.sena.adsi.noticiasapp.Retrofit_data.RetrofitApiService;
import com.sena.adsi.noticiasapp.Retrofit_data.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleActivity extends AppCompatActivity {
    private TextView titulo, detalle;
    private ImageView imagen;
    private Detalle detalleM;
    private RetrofitApiService retrofitApiService;
    public static final String URL_BASE = "http://10.0.2.2/retrofit/";
    private RecyclerView recyclerViewD;
    private Adapter adapter;
    private String doamin_image = "http://10.0.2.2/retrofit/noticia_imagenes/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);
        titulo = findViewById(R.id.txttitulo_detalle);
        detalle = findViewById(R.id.detalle_noticia);
        imagen = findViewById(R.id.img_detalle);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detalle Noticia");

        Intent i = getIntent();
        String idnoticia = i.getStringExtra("idnoticia");
        Toast.makeText(this, "Id Noticia"+idnoticia, Toast.LENGTH_SHORT).show();
        retrofitApiService = RetrofitClient.getApiService();
        initValues();
    }
    private void initValues(){
        detalleM = (Detalle) getIntent().getExtras()
                .getSerializable("idnoticia");
        //imgItemDetail.setImageResource(Integer.parseInt(itemDetail.getImgResource()));
        Picasso.get()
                .load(doamin_image+detalleM.getImagen())
                .into(imagen);
        titulo.setText(detalleM.getTitulo());
        detalle.setText(detalleM.getDetalle());
    }

}