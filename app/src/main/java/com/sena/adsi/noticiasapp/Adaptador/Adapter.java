package com.sena.adsi.noticiasapp.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sena.adsi.noticiasapp.DetalleActivity;
import com.sena.adsi.noticiasapp.Modelo.Noticia;
import com.sena.adsi.noticiasapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Noticia> noticias;
    private ArrayList<Noticia> noticiasOriginal = new ArrayList<>();
    private RecyclerItemClick itemClick;
    private String doamin_image = "http://10.0.2.2/retrofit/noticia_imagenes/";

    /*public Adapter(ArrayList<Noticia> noticias, RecyclerItemClick recyclerItemClick) {
        //this.inflater = LayoutInflater.from(context);

        this.noticias = noticias;
    }*/
    public Adapter(Context context, ArrayList<Noticia> noticias, RecyclerItemClick itemClick) {
        this.inflater = LayoutInflater.from(context);
        this.itemClick = itemClick;
        this.noticias = noticias;
        noticiasOriginal.addAll(noticias);
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String titulo = noticias.get(position).getTitulo();
        String fecha  = noticias.get(position).getFecha();
        String imagen = doamin_image+noticias.get(position).getImagen();

        holder.txtTitulo.setText(titulo);
        holder.txtFecha.setText(fecha);
        Picasso.get().load(imagen)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imagen);
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtFecha;
        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetalleActivity.class);
                    intent.putExtra("idnoticia", noticias.
                            get(getAdapterPosition()).getIdnoticia());
                    v.getContext().startActivity(intent);
                }
            });
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            imagen =  itemView.findViewById(R.id.imageNt);

        }
    }
    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            noticias.clear();
            noticias.addAll(noticiasOriginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<Noticia> collect = noticias.stream()
                        .filter(i -> i.getTitulo()
                                .toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                noticias.clear();
                noticias.addAll(collect);
            }
            else {
                noticias.clear();
                for (Noticia i : noticiasOriginal) {
                    if (i.getTitulo().toLowerCase().contains(strSearch)) {
                        noticias.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    public interface RecyclerItemClick {
        void itemClick(Noticia noticia);
    }
}
