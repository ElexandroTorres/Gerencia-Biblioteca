package com.imd0509.gerenciabiblioteca.ui.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Usuario;

public class DetalhesUsuarioActivity extends AppCompatActivity {
    private Usuario usuario;
    private TextView tvQtnEmprestimosAtual;
    private TextView tvQtnEmprestimosTotal;
    private EditText etNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_usuario);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        tvQtnEmprestimosAtual = findViewById(R.id.tv_qtn_emprestimos_atual);
        tvQtnEmprestimosTotal = findViewById(R.id.tv_qtn_emprestimos_total);
        etNome = findViewById(R.id.et_nome);

        tvQtnEmprestimosAtual.setText(String.valueOf(usuario.getEmprestimos().size()));
        tvQtnEmprestimosTotal.setText(String.valueOf(usuario.getEmprestimos().size()));
        etNome.setText(usuario.getNome());
    }
}