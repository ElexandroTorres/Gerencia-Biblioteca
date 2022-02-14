package com.imd0509.gerenciabiblioteca.ui.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.dao.UsuariosDAO;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.util.ArrayList;

public class DetalhesUsuarioActivity extends AppCompatActivity {
    private Usuario usuario;
    private TextView tvQtnEmprestimosTotal;
    private EditText etCpf;
    private EditText etNome;
    private EditText etEmail;
    private EditText etCep;
    private EditText etBairro;
    private EditText etRua;
    private EditText etNumero;
    private EditText etComplemento;
    private Button btnAtualizar;

    public void onClickAtualizar(View view) {
        usuario.setNome(etNome.getText().toString());
        usuario.setEmail(etEmail.getText().toString());
        usuario.setCep(etCep.getText().toString());
        usuario.setBairro(etBairro.getText().toString());
        usuario.setRua(etRua.getText().toString());
        usuario.setNumero(etNumero.getText().toString());
        usuario.setComplemento(etComplemento.getText().toString());

        UsuariosDAO usuariosDAO = new UsuariosDAO(DetalhesUsuarioActivity.this);
        if (usuariosDAO.atualizar(usuario)) {
            Intent intent = new Intent();
            intent.putExtra("usuario", usuario);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(DetalhesUsuarioActivity.this, "Erro ao atualizar usuário", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_usuario);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        tvQtnEmprestimosTotal = findViewById(R.id.tv_qtn_emprestimos_total);
        etCpf = findViewById(R.id.et_cpf);
        etNome = findViewById(R.id.et_nome);
        etEmail = findViewById(R.id.et_email);
        etCep = findViewById(R.id.et_cep);
        etBairro = findViewById(R.id.et_bairro);
        etRua = findViewById(R.id.et_rua);
        etNumero = findViewById(R.id.et_numero);
        etComplemento = findViewById(R.id.et_complemento);
        btnAtualizar = findViewById(R.id.btn_atualizar_usuario);

        tvQtnEmprestimosTotal.setText(String.valueOf(usuario.getEmprestimos()));
        etCpf.setText(usuario.getCpf());
        etNome.setText(usuario.getNome());
        etEmail.setText(usuario.getEmail());
        etCep.setText(usuario.getCep());
        etBairro.setText(usuario.getBairro());
        etRua.setText(usuario.getRua());
        etNumero.setText(usuario.getNumero());
        etComplemento.setText(usuario.getComplemento());

        etCpf.setEnabled(false);

        btnAtualizar.setOnClickListener(this::onClickAtualizar);

    }
}