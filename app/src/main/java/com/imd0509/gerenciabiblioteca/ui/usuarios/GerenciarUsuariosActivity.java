package com.imd0509.gerenciabiblioteca.ui.usuarios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.UsuariosAdapter;
import com.imd0509.gerenciabiblioteca.model.Usuario;
import com.imd0509.gerenciabiblioteca.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class GerenciarUsuariosActivity extends AppCompatActivity implements UsuariosAdapter.ItemUsuarioListener {
    private List<Usuario> usuarios;
    private UsuariosAdapter usuariosAdapter;
    private RecyclerView rvUsuarios;
    private FloatingActionButton fabUsuario;

    public void onClickFab(View view) {
        Intent intent = new Intent(GerenciarUsuariosActivity.this, AdicionarUsuarioActivity.class);
//        intent
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_usuarios);

        Usuario test = new Usuario("Vinicius", new ArrayList<>());
        usuarios = new ArrayList<>();
        usuarios.add(test);

        usuariosAdapter = new UsuariosAdapter(usuarios, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GerenciarUsuariosActivity.this);
        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(layoutManager);
        rvUsuarios.setHasFixedSize(true);
        rvUsuarios.setAdapter(usuariosAdapter);

        fabUsuario = findViewById(R.id.fabUsuario);
        fabUsuario.setOnClickListener(this::onClickFab);
    }

    @Override
    public void onItemClick(int position) {
        Intent informacoesIntent = new Intent(GerenciarUsuariosActivity.this, DetalhesUsuarioActivity.class);
        informacoesIntent.putExtra("usuario", usuarios.get(position));
        startActivity(informacoesIntent);
    }
}