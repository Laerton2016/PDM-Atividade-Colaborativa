package br.edu.ifpb.atividadecolaborativa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.atividadecolaborativa.modelo.PostoDeCombustivel;

/**
 * Created by Edilva on 26/03/2018.
 */

public class PostoDeCombustivelDAO {

    private HelperDao helperDao;
    private Context context;

    public PostoDeCombustivelDAO(Context context) {
        this.helperDao = new HelperDao(context);
    }

    public void salvarPosto(PostoDeCombustivel posto) {
        SQLiteDatabase db = helperDao.getWritableDatabase();
        ContentValues dados = pegaDadosDoPosto(posto);
        db.insert("Postos", null, dados);
    }

    public List<PostoDeCombustivel> listarPostos() {
        String sql = "SELECT * FROM Postos;";
        SQLiteDatabase db = helperDao.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        List<PostoDeCombustivel> postos = new ArrayList<>();

        while (cursor.moveToNext()) {
            PostoDeCombustivel posto = dadosDoPosto(cursor);

            postos.add(posto);
        }
        cursor.close();

        return postos;
    }

    public PostoDeCombustivel buscarPosto(Long id) {
        String sql = "SELECT * FROM Postos WHERE id = ?;";
        SQLiteDatabase db = helperDao.getReadableDatabase();
        String[] params = {id.toString()};
        Cursor cursor = db.rawQuery(sql, params);
        PostoDeCombustivel postoDeCombustivel = dadosDoPosto(cursor);
        return postoDeCombustivel;
    }

    private ContentValues pegaDadosDoPosto(PostoDeCombustivel posto) {
        ContentValues dados = new ContentValues();
        dados.put("nome", posto.getNome());
        dados.put("uf", posto.getUf());
        dados.put("cidade", posto.getCidade());
        return dados;
    }

    private PostoDeCombustivel dadosDoPosto(Cursor cursor) {
        PostoDeCombustivel posto = new PostoDeCombustivel();
        posto.setId(cursor.getLong(cursor.getColumnIndex("id")));
        posto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        posto.setUf(cursor.getString(cursor.getColumnIndex("uf")));
        posto.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
        return posto;
    }

    public void close() {
        helperDao.close();
    }
}
