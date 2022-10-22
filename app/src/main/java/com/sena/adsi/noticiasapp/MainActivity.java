package com.sena.adsi.noticiasapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sena.adsi.noticiasapp.Adaptador.Adapter;
import com.sena.adsi.noticiasapp.Modelo.Detalle;
import com.sena.adsi.noticiasapp.Modelo.Noticia;
import com.sena.adsi.noticiasapp.Retrofit_data.RetrofitApiService;
import com.sena.adsi.noticiasapp.Retrofit_data.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements Adapter.RecyclerItemClick, SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<Noticia> noticias;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        noticias = new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitApiService = RetrofitClient.getApiService();
        svSearch.setOnQueryTextListener(this);
        mostrarNoticias();
    }

    private void mostrarNoticias() {
        Call<List<Noticia>> call = retrofitApiService.getNoticias();
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call,
                                   Response<List<Noticia>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error codigo"
                            + response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Noticia> noticias = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager
                        (MainActivity.this));
                adapter = new Adapter((Context) MainActivity.this,
                        (ArrayList<Noticia>) noticias, MainActivity.this);

                //adapter = new Adapter((Context) MainActivity.this, (ArrayList<Noticia>) noticias);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo en conexion"
                        +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        adapter.filter(newText);
        return false;
    }

    @Override
    public void itemClick(Noticia noticia) {
        /*Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("idnotica", noticias);
        startActivity(intent);*/
    }
}