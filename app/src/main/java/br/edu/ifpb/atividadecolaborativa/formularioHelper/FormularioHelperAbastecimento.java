package br.edu.ifpb.atividadecolaborativa.formularioHelper;

import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ifpb.atividadecolaborativa.FormularioAbastecimentoActivity;
import br.edu.ifpb.atividadecolaborativa.R;
import br.edu.ifpb.atividadecolaborativa.modelo.Abastecimento;

/**
 * Created by Edilva on 31/03/2018.
 */

public class FormularioHelperAbastecimento {

    private final EditText campoQtdeLitros;
    private final EditText campoValorLitro;
    private final EditText campoQuilometragem;
    private final Spinner campoPostoDeCombustivel;
    private final Spinner campoTipoDeCombustivel;

    private Abastecimento abastecimento;


    public FormularioHelperAbastecimento(FormularioAbastecimentoActivity activity) {
        this.campoQtdeLitros = (EditText) activity.findViewById(R.id.form_quant_litros);
        this.campoValorLitro = (EditText) activity.findViewById(R.id.form_valor_litro);
        this.campoQuilometragem = (EditText) activity.findViewById(R.id.form_quilometragem);
        this.campoPostoDeCombustivel = (Spinner) activity.findViewById(R.id.postos_spinner);
        this.campoTipoDeCombustivel = (Spinner) activity.findViewById(R.id.tipo_combust_spinner);
        this.abastecimento = new Abastecimento();
    }
}
