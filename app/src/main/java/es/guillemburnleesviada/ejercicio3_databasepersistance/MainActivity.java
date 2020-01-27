package es.guillemburnleesviada.ejercicio3_databasepersistance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import es.guillemburnleesviada.ejercicio3_databasepersistance.adapters.PeliculasAdapter;
import es.guillemburnleesviada.ejercicio3_databasepersistance.pojo.Pelicula;
import es.guillemburnleesviada.ejercicio3_databasepersistance.sqlite.OrmHelper;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Pelicula> listaPeliculas;
    private PeliculasAdapter peliculasAdapter;

    //BD ORM Helper
    private OrmHelper helper;
    private Dao peliculasDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        helper = OpenHelperManager.getHelper(this, OrmHelper.class);

        try {
            peliculasDao = helper.getPeliculasDao();
            listaPeliculas = (ArrayList<Pelicula>) peliculasDao.queryForAll();
        } catch (SQLException e) {
            listaPeliculas = new ArrayList<>();
        }

        listView = findViewById(R.id.listViewMain);
        peliculasAdapter = new PeliculasAdapter(this, R.layout.fila_resource, listaPeliculas);
        listView.setAdapter(peliculasAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pelicula pelicula = (Pelicula) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(MainActivity.this, MostrarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("peliculaId", pelicula.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPelicula().show();
            }
        });
    }

    private AlertDialog crearPelicula(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.alert_pelicula, null);


        final EditText txtTitulo = layout.findViewById(R.id.txtTituloAlert);
        final Spinner spinnerGenero = layout.findViewById(R.id.spinnerAlert);
        final EditText txtDescripcion = layout.findViewById(R.id.txtDescripcionAlert);
        final RatingBar rbAlert = layout.findViewById(R.id.rbAlert);


        builder.setTitle("Meter Pelicula");
        builder.setNeutralButton("Cerrar", null);
        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String titulo = txtTitulo.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String genero = (String) spinnerGenero.getSelectedItem();
                float rating = rbAlert.getRating();

                Pelicula pelicula = new Pelicula(titulo, genero, descripcion, rating);

                try {
                    peliculasDao.create(pelicula);
                    listaPeliculas.add(pelicula);
                    peliculasAdapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });

        builder.setView(layout);

        return builder.create();
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            listaPeliculas.clear();
            listaPeliculas = (ArrayList<Pelicula>) peliculasDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        peliculasAdapter = new PeliculasAdapter(this, R.layout.fila_resource, listaPeliculas);
        listView.setAdapter(peliculasAdapter);

    }

}
