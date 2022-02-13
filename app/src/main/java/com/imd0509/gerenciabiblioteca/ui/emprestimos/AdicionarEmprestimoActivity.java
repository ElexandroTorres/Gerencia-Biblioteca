package com.imd0509.gerenciabiblioteca.ui.emprestimos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.dao.EmprestimosDAO;
import com.imd0509.gerenciabiblioteca.model.Emprestimo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdicionarEmprestimoActivity extends AppCompatActivity {

    private EditText etAddEmprestimo;
    private EditText etEmprestimoUsuario;
    private EditText getEtEmprestimoLivro;
    private Emprestimo emprestimoAtual;

    private Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_emprestimo);

        etAddEmprestimo = findViewById(R.id.etAddEmprestimo);
        etEmprestimoUsuario = findViewById(R.id.etEmpretimoUsuario);
        getEtEmprestimoLivro = findViewById(R.id.etEmprestimoLivro);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        Intent it = getIntent();

        try{
            //edita emprestimo
            emprestimoAtual = (Emprestimo) it.getExtras().getSerializable("emprestimo");
        }catch(Exception e){
            //criar um novo empréstimo
            emprestimoAtual = null;
        }

        if(emprestimoAtual != null){
            etAddEmprestimo.setText(emprestimoAtual.getNome());
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmprestimosDAO emprestimosDAO = new EmprestimosDAO(getApplicationContext());


                if(emprestimoAtual != null){//editar empréstimo

                    Date dataAtual = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String dataEmprestimo = dateFormat.format(dataAtual);

                    String nome = etAddEmprestimo.getText().toString();
                    String usuario = etEmprestimoUsuario.getText().toString();
                    String livro = getEtEmprestimoLivro.getText().toString();

                    int usuarioId = Integer.parseInt(usuario);
                    int livroId = Integer.parseInt(livro);

                    if(!nome.isEmpty()){
                        emprestimoAtual.setNome(nome);
                        emprestimoAtual.setUsuarioId(usuarioId);
                        emprestimoAtual.setLivroId(livroId);
                        emprestimoAtual.setDataEmprestimo(dataEmprestimo);
                        if(emprestimosDAO.atualizar(emprestimoAtual)){
                            Toast.makeText(getApplicationContext(), "Empréstimo atualizado", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else { //adicionar empréstimo

                    Date dataAtual = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String dataEmprestimo = dateFormat.format(dataAtual);

                    String nome = etAddEmprestimo.getText().toString();
                    String usuario = etEmprestimoUsuario.getText().toString();
                    String livro = getEtEmprestimoLivro.getText().toString();

                    int usuarioId = Integer.parseInt(usuario);
                    int livroId = Integer.parseInt(livro);

                    if(!nome.isEmpty()){
                        Emprestimo emprestimo = new Emprestimo();
                        emprestimo.setNome(nome);
                        emprestimo.setUsuarioId(usuarioId);
                        emprestimo.setLivroId(livroId);
                        emprestimo.setDataEmprestimo(dataEmprestimo);
                        if(emprestimosDAO.salvar(emprestimo)){
                            Toast.makeText(getApplicationContext(), "Empréstimo cadastrado", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao cadastrar Empréstimo", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
}