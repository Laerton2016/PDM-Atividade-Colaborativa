package br.edu.ifpb.atividadecolaborativa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.atividadecolaborativa.modelo.Usuario;

/**
 * Created by Edilva on 26/03/2018.
 */

public class UsuarioDAO {

    private HelperDao helperDao;
    private Context context;

    public UsuarioDAO(Context context) {
        this.helperDao = new HelperDao(context);
    }

    public void salvarUsuario(Usuario usuario) {
        SQLiteDatabase db = helperDao.getWritableDatabase();
        ContentValues dados = pegaDadosDoUsuario(usuario);
        db.insert("Usuarios", null, dados);
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM Usuarios;";
        SQLiteDatabase db = helperDao.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        List<Usuario> usuarios = new ArrayList<>();

        while (cursor.moveToNext()) {
            Usuario usuario = dadosDoUsuario(cursor);
            usuarios.add(usuario);
        }
        cursor.close();

        return usuarios;
    }

    public Usuario buscarUsuario(Long id) {
        String sql = "SELECT * FROM Usuarios WHERE id = ?;";
        SQLiteDatabase db = helperDao.getReadableDatabase();
        String[] params = {id.toString()};
        Cursor cursor = db.rawQuery(sql, params);
        Usuario usuario = null;
        if(cursor.moveToFirst()){
            usuario = dadosDoUsuario(cursor);
        }
        return usuario;
    }

    public Usuario login(String email, String senha) {
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?;";
        SQLiteDatabase db = helperDao.getReadableDatabase();
        String[] params = {email, senha};
        Cursor cursor = db.rawQuery(sql, params);
        Usuario usuario = null;
        if(cursor.moveToFirst()){
            usuario = dadosDoUsuario(cursor);
        }
        return usuario;
    }

    private ContentValues pegaDadosDoUsuario(Usuario usuario) {
        ContentValues dados = new ContentValues();
        dados.put("nome", usuario.getNome());
        dados.put("email", usuario.getEmail());
        dados.put("senha", usuario.getSenha());
        return dados;
    }

    private Usuario dadosDoUsuario(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getLong(cursor.getColumnIndex("id")));
        usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        usuario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        return usuario;
    }

    public void close() {
        helperDao.close();
    }
}
