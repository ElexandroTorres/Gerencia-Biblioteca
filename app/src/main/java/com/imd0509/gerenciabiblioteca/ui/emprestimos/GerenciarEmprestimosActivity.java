package com.imd0509.gerenciabiblioteca.ui.emprestimos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.EmprestimosAdapter;
import com.imd0509.gerenciabiblioteca.dao.EmprestimosDAO;
import com.imd0509.gerenciabiblioteca.model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class GerenciarEmprestimosActivity extends AppCompatActivity {

   FloatingActionButton fabAdd;
   RecyclerView rvEmprestimos;

   EmprestimosAdapter emprestimosAdapter;

   List<Emprestimo> meusEmprestimos = new ArrayList<Emprestimo>();

   Emprestimo emprestimoSelecionado = new Emprestimo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_emprestimos);

        fabAdd = findViewById(R.id.fabAdd);
        rvEmprestimos = findViewById(R.id.rvEmprestimos);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), AdicionarEmprestimoActivity.class);
                startActivity(it);
            }
        });

    }

    public void carregarEmprestimos(){

        EmprestimosDAO emprestimosDAO = new EmprestimosDAO(getApplicationContext());
        meusEmprestimos = emprestimosDAO.listar();

        //2. Exibir a lista de empréstimos

        //2.1 Configurar o adapter

        emprestimosAdapter = new EmprestimosAdapter(meusEmprestimos);

        emprestimosAdapter.implementaAoClicarNoItem(new EmprestimosAdapter.AoClicarNoItem() {
            @Override
            public void clicouNoEmprestimo(int pos) {

                //Editar Empréstimo

                Emprestimo emprestimo = new Emprestimo();
                emprestimo = meusEmprestimos.get(pos);

                Intent it = new Intent(getApplicationContext(), AdicionarEmprestimoActivity.class);
                it.putExtra("emprestimo",emprestimo);
                startActivity(it);
            }

            @Override
            public void pressionouEmprestimo(int pos) {

                emprestimoSelecionado = meusEmprestimos.get(pos);

                AlertDialog.Builder dialog = new AlertDialog.Builder(GerenciarEmprestimosActivity.this);

                dialog.setTitle("Confirmar Exclusão");

                dialog.setMessage("Deseja excluir o emprestimo: "+emprestimoSelecionado.getNome()+ " ?");

                dialog.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EmprestimosDAO emprestimosDAO = new EmprestimosDAO(getApplicationContext());
                        if (emprestimosDAO.deletar(emprestimoSelecionado)){
                            Toast.makeText(getApplicationContext(), "Emprestimo foi removido", Toast.LENGTH_SHORT).show();
                            carregarEmprestimos();
                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao deletar o emprestimo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não", null);
                dialog.create();
                dialog.show();
            }
        });

        //2. configuração recycler view

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvEmprestimos.setLayoutManager(layoutManager);
        rvEmprestimos.setHasFixedSize(true);
        rvEmprestimos.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        rvEmprestimos.setAdapter(emprestimosAdapter);


    }

    @Override
    protected void onStart() {
        this.carregarEmprestimos();
        super.onStart();
    }

}