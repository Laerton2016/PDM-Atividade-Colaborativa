package br.edu.ifpb.atividadecolaborativa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifpb.atividadecolaborativa.dao.UsuarioDAO;
import br.edu.ifpb.atividadecolaborativa.formularioHelper.FormularioHelperLogin;
import br.edu.ifpb.atividadecolaborativa.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    private FormularioHelperLogin helperLogin;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helperLogin = new FormularioHelperLogin(this);

        Button buttonLogin = (Button) findViewById(R.id.login_botao_entrar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO dao = new UsuarioDAO(MainActivity.this);
                Usuario usuario = dao.login(helperLogin.getCampoEmail(), helperLogin.getCampoSenha());
                dao.close();
                if(usuario != null) {
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putLong("user", usuario.getId());
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, ListaAbastecimentoPorDataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Email ou senha inválidos!", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button buttonCriarConta = (Button) findViewById(R.id.login_botao_criar_conta);
        buttonCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraFormularioUsuario = new Intent(MainActivity.this, FormularioUsuarioActivity.class);
                startActivity(intentVaiPraFormularioUsuario);
            }
        });

    }
}
