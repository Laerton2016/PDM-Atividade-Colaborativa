package br.edu.ifpb.atividadecolaborativa.formularioHelper;

import android.widget.EditText;

import br.edu.ifpb.atividadecolaborativa.MainActivity;
import br.edu.ifpb.atividadecolaborativa.R;

/**
 * Created by Edilva on 31/03/2018.
 */

public class FormularioHelperLogin {

    private final EditText campoEmail;
    private final EditText campoSenha;

    public FormularioHelperLogin(MainActivity activity) {
        this.campoEmail = (EditText) activity.findViewById(R.id.login_email);
        this.campoSenha = (EditText) activity.findViewById(R.id.login_senha);
    }

    public String getCampoEmail() {
        return campoEmail.getText().toString();
    }

    public String getCampoSenha() {
        return campoSenha.getText().toString();
    }
}
