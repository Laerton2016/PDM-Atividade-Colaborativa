package br.edu.ifpb.atividadecolaborativa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.atividadecolaborativa.modelo.Abastecimento;
import br.edu.ifpb.atividadecolaborativa.modelo.PostoDeCombustivel;
import br.edu.ifpb.atividadecolaborativa.modelo.TipoDeCombustivel;
import br.edu.ifpb.atividadecolaborativa.modelo.Usuario;

/**
 * Created by Edilva on 30/03/2018.
 */

public class AbastecimentoDAO {

    private HelperDao helperDao;
    private Context context;

    public AbastecimentoDAO(Context context) {
        this.context = context;
        this.helperDao = new HelperDao(context);
    }

    public void salvarAbastecimento(Abastecimento abastecimento) {
        SQLiteDatabase db = helperDao.getWritableDatabase();
        ContentValues dados = pegaDadosDoAbastecimento(abastecimento);
        db.insert("Abastecimentos", null, dados);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Abastecimento> listarAbastecimentos() {
        String sql = "SELECT * FROM Abastecimentos;";
        SQLiteDatabase db = helperDao.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        List<Abastecimento> abastecimentos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Abastecimento abastecimento = dadosDoAbastecimento(cursor);

            abastecimentos.add(abastecimento);
        }
        cursor.close();

        return abastecimentos;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Abastecimento buscarAbastecimento(Long id) {
        String sql = "SELECT * FROM Abastecimentos WHERE id = ?;";
        SQLiteDatabase db = helperDao.getReadableDatabase();
        String[] params = {id.toString()};
        Cursor cursor = db.rawQuery(sql, params);
        Abastecimento abastecimento = dadosDoAbastecimento(cursor);
        return abastecimento;
    }

    private ContentValues pegaDadosDoAbastecimento(Abastecimento abastecimento) {
        ContentValues dados = new ContentValues();
        dados.put("tipo_combustivel", abastecimento.getTipoDeCombustivel().getDescricao());
        dados.put("qtde_litros", abastecimento.getQtdeLitros());
        dados.put("valor_litro", abastecimento.getValorLitro());
        dados.put("valor_pago", abastecimento.getValorPago());
        dados.put("quilometragem", abastecimento.getQuilometragem());
        dados.put("id_usuario", abastecimento.getUsuario().getId());
        dados.put("id_posto", abastecimento.getPostoDeCombustivel().getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dados.put("horario", dateFormat.format(abastecimento.getHorario()));
        return dados;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Abastecimento dadosDoAbastecimento(Cursor cursor) {
        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setId(cursor.getLong(cursor.getColumnIndex("id")));
        String tipoDeCombustivel = cursor.getString(cursor.getColumnIndex("tipo_combustivel"));
        abastecimento.setTipoDeCombustivel(TipoDeCombustivel.valueOf(tipoDeCombustivel));
        abastecimento.setQtdeLitros(cursor.getDouble(cursor.getColumnIndex("qtde_litros")));
        abastecimento.setValorLitro(cursor.getDouble(cursor.getColumnIndex("valor_litro")));
        abastecimento.setValorPago(cursor.getDouble(cursor.getColumnIndex("valor_pago")));
        abastecimento.setQuilometragem(cursor.getDouble(cursor.getColumnIndex("quilometragem")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(cursor.getString(cursor.getColumnIndex("horario")), formatter);
        abastecimento.setHorario(dateTime);
        UsuarioDAO daoUsuario = new UsuarioDAO(context);
        Long idUsuario = cursor.getLong(cursor.getColumnIndex("id_usuario"));
        abastecimento.setUsuario(daoUsuario.buscarUsuario(idUsuario));
        daoUsuario.close();
        PostoDeCombustivelDAO daoPosto = new PostoDeCombustivelDAO(context);
        Long idPosto = cursor.getLong(cursor.getColumnIndex("id_posto"));
        abastecimento.setPostoDeCombustivel(daoPosto.buscarPosto(idPosto));
        daoPosto.close();

        return abastecimento;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Abastecimento> abastecimentosDoUsuario(Usuario usuario) {
        String sql = "SELECT * FROM Abastecimentos WHERE id_usuario ?;";
        SQLiteDatabase db = helperDao.getReadableDatabase();

        String[] params = {usuario.getId().toString()};
        Cursor cursor = db.rawQuery(sql, params);
        List<Abastecimento> abastecimentos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Abastecimento abastecimento = dadosDoAbastecimento(cursor);

            abastecimentos.add(abastecimento);
        }
        cursor.close();

        return abastecimentos;
    }

    public void close() {
        helperDao.close();
    }
}
