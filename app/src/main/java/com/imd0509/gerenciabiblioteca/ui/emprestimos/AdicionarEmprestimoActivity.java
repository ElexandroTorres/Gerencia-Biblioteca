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
import com.imd0509.gerenciabiblioteca.dao.LivrosDAO;
import com.imd0509.gerenciabiblioteca.dao.UsuariosDAO;
import com.imd0509.gerenciabiblioteca.model.Emprestimo;
import com.imd0509.gerenciabiblioteca.model.Livro;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

                    Calendar cal = Calendar.getInstance();
                    Date now = cal.getTime();
                    cal.add(Calendar.DAY_OF_MONTH, 15);
                    Date date  = cal.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String dataEmprestimo = dateFormat.format(now);
                    String dataDevolucao = dateFormat.format(date);

                    String nome = etAddEmprestimo.getText().toString();
                    String livro = getEtEmprestimoLivro.getText().toString();
                    String userCPF = getEtEmprestimoLivro.getText().toString();

                    int livroId = Integer.parseInt(livro);

                    if(!nome.isEmpty()){
                        emprestimoAtual.setNome(nome);
                        emprestimoAtual.setUsuarioId(userCPF);
                        emprestimoAtual.setLivroId(livroId);
                        emprestimoAtual.setDataEmprestimo(dataEmprestimo);
                        emprestimoAtual.setDataDevolucao(dataDevolucao);
                        if(emprestimosDAO.atualizar(emprestimoAtual)){
                            Toast.makeText(getApplicationContext(), "Empréstimo atualizado", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else { //adicionar empréstimo

                    Calendar cal = Calendar.getInstance();
                    Date now = cal.getTime();
                    cal.add(Calendar.DAY_OF_MONTH, 15);
                    Date date  = cal.getTime();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String dataEmprestimo = dateFormat.format(now);
                    String dataDevolucao = dateFormat.format(date);


                    String nome = etAddEmprestimo.getText().toString();
                    String cpfUser = etEmprestimoUsuario.getText().toString();
                    String livro = getEtEmprestimoLivro.getText().toString();

                    int livroId = Integer.parseInt(livro);

                    if(!nome.isEmpty()){
                        LivrosDAO livrosDAO = new LivrosDAO(AdicionarEmprestimoActivity.this);
                        UsuariosDAO usuariosDAO = new UsuariosDAO(AdicionarEmprestimoActivity.this);
                        Usuario user = usuariosDAO.getUsuario(cpfUser);
                        Livro book = livrosDAO.getLivro(livroId);
                        if(book == null || user == null){
                            Toast.makeText(getApplicationContext(), "Erro ao cadastrar Empréstimo pois o livro ou usuario ainda não foi encontrado", Toast.LENGTH_SHORT).show();
                        }else {

                            Emprestimo emprestimo = new Emprestimo();
                            emprestimo.setNome(nome);
                            emprestimo.setUsuarioId(cpfUser);
                            emprestimo.setLivroId(livroId);
                            emprestimo.setDataEmprestimo(dataEmprestimo);
                            emprestimo.setDataDevolucao(dataDevolucao);
                            if (emprestimosDAO.salvar(emprestimo)) {
                                Toast.makeText(getApplicationContext(), "Empréstimo cadastrado", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Erro ao cadastrar Empréstimo", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            }
        });

    }
}