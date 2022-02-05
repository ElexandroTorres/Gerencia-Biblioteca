package com.imd0509.gerenciabiblioteca.ui.usuarios;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.UsuariosAdapter;
import com.imd0509.gerenciabiblioteca.dao.UsuariosDAO;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.text.ParseException;
import java.util.List;

public class GerenciarUsuariosActivity extends AppCompatActivity implements UsuariosAdapter.ItemUsuarioListener {
    private List<Usuario> usuarios;
    private UsuariosAdapter usuariosAdapter;
    private RecyclerView rvUsuarios;
    private FloatingActionButton fabUsuario;

    ActivityResultLauncher<Intent> resultadoCadastro = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                Usuario novoUsuario = (Usuario) result.getData().getExtras().getSerializable("usuario");
                usuarios.add(novoUsuario);
                int index = usuarios.size() - 1;
                usuariosAdapter.notifyItemInserted(index);
            }
        }
    });

    ActivityResultLauncher<Intent> resultadoAtualizacao = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                Usuario novoUsuario = (Usuario) result.getData().getExtras().getSerializable("usuario");
                int index = usuarios.indexOf(novoUsuario);
                usuarios.get(index).update(novoUsuario);

                usuariosAdapter.notifyItemChanged(index);
            }
        }
    });


    public void onClickFab(View view) {
        Intent intent = new Intent(GerenciarUsuariosActivity.this, AdicionarUsuarioActivity.class);
        resultadoCadastro.launch(intent);
    }

    private void loadData() {
        try {
            UsuariosDAO usuariosDAO = new UsuariosDAO(GerenciarUsuariosActivity.this);
            usuarios = usuariosDAO.listar();
        } catch (ParseException e) {
            Log.i("Error", e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_usuarios);

        loadData();

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
        Intent intent = new Intent(GerenciarUsuariosActivity.this, DetalhesUsuarioActivity.class);
        intent.putExtra("usuario", usuarios.get(position));
        resultadoAtualizacao.launch(intent);
    }
}