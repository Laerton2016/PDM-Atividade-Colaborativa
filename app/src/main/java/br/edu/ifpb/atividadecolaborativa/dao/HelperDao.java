package br.edu.ifpb.atividadecolaborativa.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edilva on 27/03/2018.
 */

public class HelperDao extends SQLiteOpenHelper {

    public HelperDao(Context context) {
        super(context, "GastosCombustivel", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE Usuarios(id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "senha TEXT NOT NULL);";
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE Postos(id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "uf TEXT NOT NULL, " +
                "cidade TEXT NOT NULL);";
        db.execSQL(sql2);

        String sql3 = "CREATE TABLE Abastecimentos(id INTEGER PRIMARY KEY, " +
                "tipo_combustivel TEXT NOT NULL, " +
                "qtde_litros REAL NOT NULL, " +
                "valor_litro REAL NOT NULL, " +
                "valor_pago REAL NOT NULL, " +
                "quilometragem REAL NOT NULL, " +
                "horario TEXT NOT NULL, " +
                "id_usuario INTEGER NOT NULL, " +
                "id_posto INTEGER NOT NULL, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuario (id), " +
                "FOREIGN KEY (id_posto) REFERENCES Posto (id))";
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
