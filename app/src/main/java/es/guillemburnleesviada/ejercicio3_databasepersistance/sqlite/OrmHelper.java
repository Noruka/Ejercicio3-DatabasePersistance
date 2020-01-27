package es.guillemburnleesviada.ejercicio3_databasepersistance.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import es.guillemburnleesviada.ejercicio3_databasepersistance.configuracion.Configuracion;
import es.guillemburnleesviada.ejercicio3_databasepersistance.pojo.Pelicula;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

    private Dao<Pelicula, Integer> peliculasDao;

    public OrmHelper(Context context) {
        super(context, Configuracion.BD_NOMBRE, null, Configuracion.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Pelicula.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Pelicula.class, true);
            TableUtils.createTable(connectionSource, Pelicula.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Pelicula, Integer> getPeliculasDao() throws SQLException {
        if (peliculasDao == null){
            peliculasDao = getDao(Pelicula.class);
        }
        return peliculasDao;
    }
}
