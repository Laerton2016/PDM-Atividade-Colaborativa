package br.edu.ifpb.atividadecolaborativa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import br.edu.ifpb.atividadecolaborativa.dao.AbastecimentoDAO;
import br.edu.ifpb.atividadecolaborativa.dao.UsuarioDAO;
import br.edu.ifpb.atividadecolaborativa.modelo.Abastecimento;
import br.edu.ifpb.atividadecolaborativa.modelo.PostoDeCombustivel;
import br.edu.ifpb.atividadecolaborativa.modelo.TipoDeCombustivel;
import br.edu.ifpb.atividadecolaborativa.modelo.Usuario;
import br.edu.ifpb.atividadecolaborativa.rest.DateDeserializer;
import br.edu.ifpb.atividadecolaborativa.rest.NetworkUtils;

public class ListaAbastecimentoPorDataActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private ListView listaAbastecimentos;
    private ProgressDialog load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_abastecimento_por_data);

        listaAbastecimentos = (ListView) findViewById(R.id.lista_abastecimento_data);

        Button novoAbastecimento = (Button) findViewById(R.id.botao_novo_abastecimento);
        novoAbastecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaAbastecimentoPorDataActivity.this, FormularioAbastecimentoActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        listaAbastecimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Abastecimento abastecimento = (Abastecimento) listaAbastecimentos.getItemAtPosition(position);
                Intent intentVaiPraDescricao = new Intent(ListaAbastecimentoPorDataActivity.this, DescricaoAbastecimentoActivity.class);
                intentVaiPraDescricao.putExtra("abastecimento", abastecimento);
                startActivity(intentVaiPraDescricao);
            }
        });
    }

    private void carregaLista() {
        /*SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        Long id = settings.getLong("user", 0);

        //UsuarioDAO daoUsuario = new UsuarioDAO(ListaAbastecimentoPorDataActivity.this);
        //Usuario usuario = daoUsuario.buscarUsuario(id);
        //daoUsuario.close();

        //AbastecimentoDAO dao = new AbastecimentoDAO(this);
        List<Abastecimento> abastecimentos  = null;
        Gson gson =new Gson();
        try {
            //abastecimentos =  dao.abastecimentosDoUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dao.close();

        listaAbastecimentos.setAdapter(new ArrayAdapter<Abastecimento>(this,
                android.R.layout.simple_list_item_1, abastecimentos));*/
        GetJson gj = new GetJson();
        gj.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
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
            case R.id.menu_novo_posto:
                Intent intentNovoPosto = new Intent(ListaAbastecimentoPorDataActivity.this, FormularioPostoDeCombustivelActivity.class);
                startActivity(intentNovoPosto);
                break;
            case R.id.menu_historico_posto:
                Intent intentListaPorPosto = new Intent(ListaAbastecimentoPorDataActivity.this, ListaAbastecimentoPorPostoActivity.class);
                startActivity(intentListaPorPosto);
                break;
            case R.id.menu_novo_abastecimento:
                Intent intentNovoAbastecimento = new Intent(ListaAbastecimentoPorDataActivity.this, FormularioAbastecimentoActivity.class);
                startActivity(intentNovoAbastecimento);
                break;
            case R.id.menu_sair:
                Sair sair = new Sair(this);
                sair.sair();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GetJson extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(ListaAbastecimentoPorDataActivity.this, "Por favor Aguarde ...", "Recuperando Informações do Servidor...");

        }

        @Override
        protected String doInBackground(Void... voids) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            Long id = settings.getLong("user", 0);
            Log.i ("Id", String.valueOf(id));
            return NetworkUtils.GetJASONFromApi("http://10.3.132.157:8080/webService/webapi/Abastecimentos/byUser/" + id);


        }
        @Override
        protected void onPostExecute(String abastecimentos){



            Type collectionType = new TypeToken<List<Abastecimento>>() {}.getType();
            //Gson gs = new Gson();
            List<Abastecimento> abs = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create().fromJson(abastecimentos, collectionType);

            if (abs != null){
                listaAbastecimentos.setAdapter(new ArrayAdapter<Abastecimento>(ListaAbastecimentoPorDataActivity.this,
                        android.R.layout.simple_list_item_1, abs));
            }

            Log.i("Resultado", abastecimentos);
            load.dismiss();
        }
    }
}

