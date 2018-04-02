package br.edu.ifpb.atividadecolaborativa.formularioHelper;

import android.widget.EditText;

import br.edu.ifpb.atividadecolaborativa.FormularioPostoDeCombustivelActivity;
import br.edu.ifpb.atividadecolaborativa.R;
import br.edu.ifpb.atividadecolaborativa.modelo.PostoDeCombustivel;

/**
 * Created by Edilva on 31/03/2018.
 */

public class FormularioHelperPosto {

    private final EditText campoNome;
    private final EditText campoUf;
    private final EditText campoCidade;

    private PostoDeCombustivel postoDeCombustivel;

    public FormularioHelperPosto(FormularioPostoDeCombustivelActivity activity) {
        this.campoNome = (EditText) activity.findViewById(R.id.form_posto_nome);
        this.campoUf = (EditText) activity.findViewById(R.id.form_posto_uf);
        this.campoCidade = (EditText) activity.findViewById(R.id.form_posto_cidade);
        this.postoDeCombustivel = new PostoDeCombustivel();
    }

    public PostoDeCombustivel pegaPosto() {
        postoDeCombustivel.setNome(campoNome.getText().toString());
        postoDeCombustivel.setUf(campoUf.getText().toString());
        postoDeCombustivel.setCidade(campoCidade.getText().toString());
        return postoDeCombustivel;
    }
}
