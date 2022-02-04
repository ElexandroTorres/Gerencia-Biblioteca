package com.imd0509.gerenciabiblioteca.ui.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.util.ArrayList;

public class AdicionarUsuarioActivity extends AppCompatActivity {
    private EditText etNome;
    private Button btnSalvar;

    public void onClickSalvar(View view) {
        String nome = etNome.getText().toString();
        Usuario usuario = new Usuario(nome, new ArrayList<>());

        Intent intent = new Intent();
        intent.putExtra("usuario", usuario);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_usuario);

        etNome = findViewById(R.id.et_nome);
        btnSalvar = findViewById(R.id.btn_salvar_usuario);

        btnSalvar.setOnClickListener(this::onClickSalvar);
    }
}