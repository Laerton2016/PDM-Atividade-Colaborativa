package br.edu.ifpb.atividadecolaborativa.formularioHelper;

import android.widget.EditText;

import br.edu.ifpb.atividadecolaborativa.FormularioUsuarioActivity;
import br.edu.ifpb.atividadecolaborativa.R;
import br.edu.ifpb.atividadecolaborativa.modelo.Usuario;

/**
 * Created by Edilva on 31/03/2018.
 */

public class FormularioHelperUsuario {

    private final EditText campoNome;
    private final EditText campoEmail;
    private final EditText campoSenha;

    private Usuario usuario;

    public FormularioHelperUsuario(FormularioUsuarioActivity activity) {
        this.campoNome = (EditText) activity.findViewById(R.id.form_usuario_nome);
        this.campoEmail = (EditText) activity.findViewById(R.id.form_usuario_email);
        this.campoSenha = (EditText) activity.findViewById(R.id.form_usuario_senha);
        this.usuario = new Usuario();
    }

    public Usuario pegaUsuario() {
        usuario.setNome(campoNome.getText().toString());
        usuario.setEmail(campoEmail.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        return usuario;
    }
}
