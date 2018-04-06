package br.edu.ifpb.atividadecolaborativa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.List;

import br.edu.ifpb.atividadecolaborativa.dao.AbastecimentoDAO;
import br.edu.ifpb.atividadecolaborativa.dao.PostoDeCombustivelDAO;
import br.edu.ifpb.atividadecolaborativa.dao.UsuarioDAO;
import br.edu.ifpb.atividadecolaborativa.modelo.Abastecimento;
import br.edu.ifpb.atividadecolaborativa.modelo.PostoDeCombustivel;
import br.edu.ifpb.atividadecolaborativa.modelo.Usuario;

public class ListaAbastecimentoPorPostoActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private ListView listaAbastecimentos;
    PostoDeCombustivel posto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_abastecimento_por_posto);

        PostoDeCombustivelDAO daoPosto = new PostoDeCombustivelDAO(ListaAbastecimentoPorPostoActivity.this);
        List<PostoDeCombustivel> postos = daoPosto.listarPostos();
        daoPosto.close();

        final Spinner spinnerPostoCombustivel = (Spinner) findViewById(R.id.postos_spinner);
        spinnerPostoCombustivel.setAdapter(new ArrayAdapter<PostoDeCombustivel>(this,
                android.R.layout.simple_list_item_1, postos));

        Button buttonPesquisar = (Button) findViewById(R.id.botao_pesquisar);
        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaAbastecimentos = (ListView) findViewById(R.id.lista_abastecimento_posto);
                posto = (PostoDeCombustivel) spinnerPostoCombustivel.getSelectedItem();
                carregaLista();
            }
        });

        Button novoAbastecimento = (Button) findViewById(R.id.botao_novo_abastecimento);
        novoAbastecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaAbastecimentoPorPostoActivity.this, FormularioAbastecimentoActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });
    }

    private void carregaLista() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Long id = settings.getLong("user", 0);

        UsuarioDAO daoUsuario = new UsuarioDAO(ListaAbastecimentoPorPostoActivity.this);
        Usuario usuario = daoUsuario.buscarUsuario(id);
        daoUsuario.close();

        AbastecimentoDAO dao = new AbastecimentoDAO(this);
        List<Abastecimento> abastecimentos  = null;
        try {
            abastecimentos = dao.abastecimentosDoPosto(usuario, posto);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dao.close();

        listaAbastecimentos.setAdapter(new ArrayAdapter<Abastecimento>(this,
                android.R.layout.simple_list_item_1, abastecimentos));
    }

    @Override
    protected void onRestart() {
        carregaLista();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_historico_data:
                Intent intentListaPorData = new Intent(ListaAbastecimentoPorPostoActivity.this, ListaAbastecimentoPorDataActivity.class);
                startActivity(intentListaPorData);
                break;
            case R.id.menu_novo_posto:
                Intent intentNovoPosto = new Intent(ListaAbastecimentoPorPostoActivity.this, FormularioPostoDeCombustivelActivity.class);
                startActivity(intentNovoPosto);
                break;
            case R.id.menu_novo_abastecimento:
                Intent intentNovoAbastecimento = new Intent(ListaAbastecimentoPorPostoActivity.this, FormularioAbastecimentoActivity.class);
                startActivity(intentNovoAbastecimento);
                break;
            case R.id.menu_sair:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
