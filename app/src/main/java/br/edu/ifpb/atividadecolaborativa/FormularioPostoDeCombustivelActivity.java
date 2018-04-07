package br.edu.ifpb.atividadecolaborativa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifpb.atividadecolaborativa.dao.PostoDeCombustivelDAO;
import br.edu.ifpb.atividadecolaborativa.formularioHelper.FormularioHelperPosto;
import br.edu.ifpb.atividadecolaborativa.modelo.PostoDeCombustivel;

public class FormularioPostoDeCombustivelActivity extends AppCompatActivity {

    private FormularioHelperPosto helperPosto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomulario_posto_de_combustivel);

        helperPosto = new FormularioHelperPosto(this);

        Button buttonSalvarPosto = (Button) findViewById(R.id.botao_salvar_posto);
        buttonSalvarPosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostoDeCombustivelDAO dao = new PostoDeCombustivelDAO(FormularioPostoDeCombustivelActivity.this);
                PostoDeCombustivel postoDeCombustivel = helperPosto.pegaPosto();
                dao.salvarPosto(postoDeCombustivel);
                dao.close();
                Toast.makeText(FormularioPostoDeCombustivelActivity.this, "Posto " + postoDeCombustivel.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
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
                Intent intentListaPorData = new Intent(FormularioPostoDeCombustivelActivity.this, ListaAbastecimentoPorDataActivity.class);
                startActivity(intentListaPorData);
                break;
            case R.id.menu_historico_posto:
                Intent intentListaPorPosto = new Intent(FormularioPostoDeCombustivelActivity.this, ListaAbastecimentoPorPostoActivity.class);
                startActivity(intentListaPorPosto);
                break;
            case R.id.menu_novo_abastecimento:
                Intent intentNovoAbastecimento = new Intent(FormularioPostoDeCombustivelActivity.this, FormularioAbastecimentoActivity.class);
                startActivity(intentNovoAbastecimento);
                break;
            case R.id.menu_sair:
                Sair sair = new Sair(this);
                sair.sair();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
