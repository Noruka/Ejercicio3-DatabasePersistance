package es.guillemburnleesviada.ejercicio3_databasepersistance.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "peliculas")
public class Pelicula{

    @DatabaseField(generatedId = true, columnName = "id_pelicula")
    private int id;
    @DatabaseField(canBeNull = false)
    private String titulo;
    @DatabaseField(canBeNull = false)
    private String descripcion;
    @DatabaseField(canBeNull = false)
    private String genero;
    @DatabaseField(canBeNull = false)
    private float rating;

    public Pelicula() {
    }

    public Pelicula(String titulo, String descripcion, String genero, float rating) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
