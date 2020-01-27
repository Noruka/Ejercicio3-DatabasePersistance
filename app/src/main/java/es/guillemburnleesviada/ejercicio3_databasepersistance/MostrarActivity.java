package es.guillemburnleesviada.ejercicio3_databasepersistance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.ArrayList;

import es.guillemburnleesviada.ejercicio3_databasepersistance.pojo.Pelicula;
import es.guillemburnleesviada.ejercicio3_databasepersistance.sqlite.OrmHelper;

public class MostrarActivity extends AppCompatActivity {

    private TextView txtTitulo, txtGenero, txtDescripcion;
    private RatingBar ratingBar;

    private OrmHelper helper;
    private Dao peliculasDao;
    private ArrayList<Pelicula> listaPeliculas;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        txtTitulo = findViewById(R.id.txtTituloMostrar);
        txtDescripcion = findViewById(R.id.txtDescripcionMostrar);
        txtGenero = findViewById(R.id.txtGeneroMostrar);
        ratingBar = findViewById(R.id.rbRatingMostrar);

        helper = OpenHelperManager.getHelper(this, OrmHelper.class);

        try {
            peliculasDao = helper.getPeliculasDao();
            listaPeliculas = (ArrayList<Pelicula>) peliculasDao.queryForAll();
        } catch (SQLException e) {
            listaPeliculas = new ArrayList<>();
        }

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        id = bundle.getInt("peliculaId");

        Pelicula pelicula = new Pelicula();

        for (int i = 0; i < listaPeliculas.size(); i++) {
            if (listaPeliculas.get(i).getId() == id){
                pelicula = listaPeliculas.get(i);
            }
        }

        txtTitulo.setText(pelicula.getTitulo());
        txtGenero.setText(pelicula.getGenero());
        txtDescripcion.setText(pelicula.getDescripcion());
        ratingBar.setRating(pelicula.getRating());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.menu_mostrar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.btnBorrarMenu){

            try {
                peliculasDao.deleteById(id);

                for (int i = 0; i < listaPeliculas.size(); i++) {
                    if (listaPeliculas.get(i).getId() == id){
                        listaPeliculas.remove(i);
                    }
                }
                finish();


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return true;
    }
}
