package com.imd0509.gerenciabiblioteca.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.helpers.DBHelper;
import com.imd0509.gerenciabiblioteca.ui.emprestimos.GerenciarEmprestimosActivity;
import com.imd0509.gerenciabiblioteca.ui.livros.GerenciarLivrosActivity;
import com.imd0509.gerenciabiblioteca.ui.usuarios.GerenciarUsuariosActivity;

public class MainActivity extends AppCompatActivity {

    Button btnGerenciarLivros;
    Button btnGerenciarEmprestimos;
    Button btnGerenciarUsuarios;

    DBHelper bibliotecaDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bibliotecaDataBase = new DBHelper(getApplicationContext());

        findViewsIds();
        setListeners();
    }

    private void findViewsIds() {
        btnGerenciarLivros = findViewById(R.id.btn_gerenciar_livros);
        btnGerenciarEmprestimos = findViewById(R.id.btn_gerenciar_emprestimos);
        btnGerenciarUsuarios = findViewById(R.id.btn_gerenciar_usuarios);
    }

    private void setListeners() {
        btnGerenciarLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GerenciarLivrosActivity.class);
                startActivity(intent);
            }
        });

        btnGerenciarEmprestimos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GerenciarEmprestimosActivity.class);
                startActivity(intent);
            }
        });

        btnGerenciarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GerenciarUsuariosActivity.class);
                startActivity(intent);
            }
        });
    }
}