package br.edu.ifpb.atividadecolaborativa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.edu.ifpb.atividadecolaborativa.formularioHelper.Descricao;
import br.edu.ifpb.atividadecolaborativa.modelo.Abastecimento;

public class DescricaoAbastecimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_abastecimento);

        Intent intent = getIntent();
        Abastecimento abastecimento = (Abastecimento) intent.getSerializableExtra("abastecimento");

        Descricao descricao = new Descricao(this);
        descricao.preencheDescricao(abastecimento);
    }
}
