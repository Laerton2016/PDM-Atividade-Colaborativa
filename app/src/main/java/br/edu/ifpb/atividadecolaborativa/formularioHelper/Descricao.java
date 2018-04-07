package br.edu.ifpb.atividadecolaborativa.formularioHelper;

import android.widget.TextView;

import java.text.SimpleDateFormat;

import br.edu.ifpb.atividadecolaborativa.DescricaoAbastecimentoActivity;
import br.edu.ifpb.atividadecolaborativa.R;
import br.edu.ifpb.atividadecolaborativa.modelo.Abastecimento;

/**
 * Created by Edilva on 06/04/2018.
 */

public class Descricao {

    private final TextView posto;
    private final TextView combustivel;
    private final TextView horario;
    private final TextView qtdeLitros;
    private final TextView valorLitro;
    private final TextView valorPago;
    private final TextView quilometragem;

    public Descricao(DescricaoAbastecimentoActivity activity) {
        this.posto = (TextView) activity.findViewById(R.id.postoText);
        this.combustivel = (TextView) activity.findViewById(R.id.combustivelText);
        this.horario = (TextView) activity.findViewById(R.id.horarioText);
        this.qtdeLitros = (TextView) activity.findViewById(R.id.qtdeLitrosText);
        this.valorLitro = (TextView) activity.findViewById(R.id.valorLitroText);
        this.valorPago = (TextView) activity.findViewById(R.id.valorPagoText);
        this.quilometragem = (TextView) activity.findViewById(R.id.quilometragemText);
    }

    public void preencheDescricao(Abastecimento abastecimento) {
        this.posto.setText("Posto: " + abastecimento.getPostoDeCombustivel());
        this.combustivel.setText("Tipo de combustível: " + abastecimento.getTipoDeCombustivel());
        this.qtdeLitros.setText("Quant. de litros: " + abastecimento.getQtdeLitros());
        this.valorLitro.setText("Valor do litro: " + abastecimento.getValorLitro());
        this.valorPago.setText("Valor pago: " + abastecimento.getValorPago());
        this.quilometragem.setText("Quilometragem: " + abastecimento.getQuilometragem());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String s = dateFormat.format(abastecimento.getHorario());
        this.horario.setText("Data/Hora: " + s);
    }
}
