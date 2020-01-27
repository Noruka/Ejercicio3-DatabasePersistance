package es.guillemburnleesviada.ejercicio3_databasepersistance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import es.guillemburnleesviada.ejercicio3_databasepersistance.R;
import es.guillemburnleesviada.ejercicio3_databasepersistance.pojo.Pelicula;

public class PeliculasAdapter extends ArrayAdapter<Pelicula> {

    private Context context;
    private int resource;
    private ArrayList<Pelicula> listaPeliculas;


    public PeliculasAdapter(@NonNull Context context, int resource, @NonNull List<Pelicula> objects) {
        super(context, resource, objects);

        this.context = context;
        this.listaPeliculas = (ArrayList<Pelicula>) objects;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View fila = inflater.inflate(this.resource, null);
        TextView txtTitulo = fila.findViewById(R.id.txtNombreResource);
        RatingBar ratingBar = fila.findViewById(R.id.rbRatingResource);
        Pelicula p = listaPeliculas.get(position);
        txtTitulo.setText(p.getTitulo());
        ratingBar.setRating(p.getRating());

        return fila;
    }
}
