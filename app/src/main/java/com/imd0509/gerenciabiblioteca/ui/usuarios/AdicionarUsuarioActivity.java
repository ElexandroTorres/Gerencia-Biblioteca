package com.imd0509.gerenciabiblioteca.ui.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.dao.UsuariosDAO;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.util.ArrayList;

public class AdicionarUsuarioActivity extends AppCompatActivity {
    private EditText etCpf;
    private EditText etNome;
    private EditText etEmail;
    private EditText etCep;
    private EditText etBairro;
    private EditText etRua;
    private EditText etNumero;
    private EditText etComplemento;
    private Button btnSalvar;

    public void onClickSalvar(View view) {
        Usuario usuario = new Usuario(
                etCpf.getText().toString(),
                etNome.getText().toString(),
                etEmail.getText().toString(),
                etCep.getText().toString(),
                etBairro.getText().toString(),
                etRua.getText().toString(),
                etNumero.getText().toString(),
                etComplemento.getText().toString(),
                0);

        UsuariosDAO usuariosDAO = new UsuariosDAO(AdicionarUsuarioActivity.this);
        if (usuariosDAO.salvar(usuario)) {
            Intent intent = new Intent();
            intent.putExtra("usuario", usuario);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(AdicionarUsuarioActivity.this, "Erro ao adicionar usuário", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_usuario);

        etCpf = findViewById(R.id.et_cpf);
        etNome = findViewById(R.id.et_nome);
        etEmail = findViewById(R.id.et_email);
        etCep = findViewById(R.id.et_cep);
        etBairro = findViewById(R.id.et_bairro);
        etRua = findViewById(R.id.et_rua);
        etNumero = findViewById(R.id.et_numero);
        etComplemento = findViewById(R.id.et_complemento);
        btnSalvar = findViewById(R.id.btn_salvar_usuario);

        btnSalvar.setOnClickListener(this::onClickSalvar);
    }
}