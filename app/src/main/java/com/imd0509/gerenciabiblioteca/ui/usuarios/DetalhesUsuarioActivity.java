package com.imd0509.gerenciabiblioteca.ui.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.dao.UsuariosDAO;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.util.ArrayList;

public class DetalhesUsuarioActivity extends AppCompatActivity {
    private Usuario usuario;
    private TextView tvQtnEmprestimosAtual;
    private TextView tvQtnEmprestimosTotal;
    private EditText etNome;
    private Button btnAtualizar;

    public void onClickAtualizar(View view) {
        String nome = etNome.getText().toString();
        usuario.setNome(nome);

        UsuariosDAO usuariosDAO = new UsuariosDAO(DetalhesUsuarioActivity.this);
        if (usuariosDAO.atualizar(usuario)) {
            Intent intent = new Intent();
            intent.putExtra("usuario", usuario);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_usuario);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        tvQtnEmprestimosAtual = findViewById(R.id.tv_qtn_emprestimos_atual);
        tvQtnEmprestimosTotal = findViewById(R.id.tv_qtn_emprestimos_total);
        etNome = findViewById(R.id.et_nome);
        btnAtualizar = findViewById(R.id.btn_atualizar_usuario);

        tvQtnEmprestimosAtual.setText(String.valueOf(usuario.getEmprestimos().size()));
        tvQtnEmprestimosTotal.setText(String.valueOf(usuario.getEmprestimos().size()));
        etNome.setText(usuario.getNome());

        btnAtualizar.setOnClickListener(this::onClickAtualizar);

    }
}