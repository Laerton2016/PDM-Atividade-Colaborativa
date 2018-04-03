package br.edu.ifpb.atividadecolaborativa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.edu.ifpb.atividadecolaborativa.modelo.TipoDeCombustivel;

public class FormularioAbastecimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_abastecimento);

        Spinner spinnerTipoCombustivel = (Spinner) findViewById(R.id.tipo_combust_spinner);
        spinnerTipoCombustivel.setAdapter(new ArrayAdapter<TipoDeCombustivel>(this,
                android.R.layout.simple_list_item_1, TipoDeCombustivel.values()));

    }
}
