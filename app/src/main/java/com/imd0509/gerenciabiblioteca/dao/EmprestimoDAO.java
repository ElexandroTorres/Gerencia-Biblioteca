package com.imd0509.gerenciabiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.imd0509.gerenciabiblioteca.helpers.DBHelper;
import com.imd0509.gerenciabiblioteca.model.Emprestimo;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public EmprestimoDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    public boolean salvar (Emprestimo emprestimo){

        //1. definir o conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("nome",emprestimo.getNome());
        cv.put("usuario", emprestimo.getUsuario());
        cv.put("livro", emprestimo.getLivro());
        cv.put("data", emprestimo.getDataEmprestimo());


        try{
            escreve.insert(DBHelper.EMPRESTIMOS_NOME_TABELA ,null,cv);
            Log.i("INFO","Registro salvo com sucesso!");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar registro: "+e.getMessage());
            return false;
        }

        return true;
    }

    public List<Emprestimo> listar() {
        List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

        //1. string sql de consulta
        String sql = "SELECT * FROM "+DBHelper.EMPRESTIMOS_NOME_TABELA+ ";";

        //2. Cursor para acesso aos dados
        Cursor c = le.rawQuery(sql,null);

        //3. percorrer o cursor
        c.moveToFirst();
        while(c.moveToNext()){

            Emprestimo emprestimo = new Emprestimo();

            //Long id = c.getLong( 0 );
            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String nome = c.getString(c.getColumnIndexOrThrow("nome"));
            String usuario = c.getString(c.getColumnIndexOrThrow("usuario"));
            String livro = c.getString(c.getColumnIndexOrThrow("livro"));
            String data = c.getString(c.getColumnIndexOrThrow("data"));

            emprestimo.setId(id);
            emprestimo.setNome(nome);
            emprestimo.setUsuario(usuario);
            emprestimo.setLivro(livro);
            emprestimo.setDataEmprestimo(data);

            emprestimos.add(emprestimo);
        }
        c.close();

        return emprestimos;
    }

    public boolean atualizar(Emprestimo emprestimo){

        //1. definir conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("nome",emprestimo.getNome());
        cv.put("usuario",emprestimo.getUsuario());
        cv.put("livro", emprestimo.getLivro());
        cv.put("data", emprestimo.getDataEmprestimo());

        //2. atualizar valor no banco
        try{
            String[] args = {emprestimo.getId().toString()};
            //2.1 update(nome da tabela, conteudo para atualizar, clausula de atualização (where)
            // o argumento da condição --> ?)
            escreve.update(DBHelper.EMPRESTIMOS_NOME_TABELA,cv,"id=?",args);
            Log.i("INFO","Registro atualizado com sucesso!");
        }catch(Exception e){
            Log.i("INFO","Erro ao atualizar registro!" + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deletar(Emprestimo emprestimo){

        //1. deletar um registro de tarefa na tabela tarefas

        try{
            //id do registro que será deletado
            String[] args = {emprestimo.getId().toString()};
            escreve.delete(DBHelper.EMPRESTIMOS_NOME_TABELA,"id=?",args);
            Log.i("INFO","Registro apagado com sucesso!");
        }catch(Exception e){
            Log.i("INFO","Erro apagar registro!"+e.getMessage());
            return false;
        }
        return true;
    }


 }



