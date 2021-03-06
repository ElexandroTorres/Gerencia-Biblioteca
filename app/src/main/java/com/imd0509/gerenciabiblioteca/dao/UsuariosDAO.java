package com.imd0509.gerenciabiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.imd0509.gerenciabiblioteca.helpers.DBHelper;
import com.imd0509.gerenciabiblioteca.model.Livro;
import com.imd0509.gerenciabiblioteca.model.Usuario;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    private final SQLiteDatabase escrever;
    private final SQLiteDatabase ler;

    public UsuariosDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.escrever = dbHelper.getWritableDatabase();
        this.ler = dbHelper.getReadableDatabase();
    }

    public boolean salvar(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cpf", usuario.getCpf());
        contentValues.put("nome", usuario.getNome());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("cep", usuario.getCep());
        contentValues.put("bairro", usuario.getBairro());
        contentValues.put("rua", usuario.getRua());
        contentValues.put("numero", usuario.getNumero());
        contentValues.put("complemento", usuario.getComplemento());

        try {
            escrever.insert(DBHelper.USUARIOS_NOME_TABELA, null, contentValues);
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
            return false;
        }
        return true;
    }

    public Usuario getUsuario(String cpf) {
        String sql = "SELECT * FROM " + DBHelper.USUARIOS_NOME_TABELA + " WHERE " + DBHelper.USUARIOS_CPF + "='" + cpf + "'";
        Cursor cursor = ler.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String cep = cursor.getString(cursor.getColumnIndexOrThrow("cep"));
            String bairro = cursor.getString(cursor.getColumnIndexOrThrow("bairro"));
            String rua = cursor.getString(cursor.getColumnIndexOrThrow("rua"));
            String numero = cursor.getString(cursor.getColumnIndexOrThrow("numero"));
            String complemento = cursor.getString(cursor.getColumnIndexOrThrow("complemento"));

            Usuario usuario = new Usuario(cpf, nome, email, cep, bairro, rua, numero, complemento, 0);

            cursor.close();
            return usuario;
        }

        cursor.close();
        return null;

    }

    public List<Usuario> listar() throws ParseException {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = String.format("SELECT %s, %s.%s as %s, %s, %s, %s, %s, %s, %s, count(%s.%s) as %s " +
                        "FROM %s LEFT JOIN %s ON %s=%s GROUP BY %s;",
                DBHelper.USUARIOS_CPF, DBHelper.USUARIOS_NOME_TABELA, DBHelper.USUARIOS_NOME, DBHelper.USUARIOS_NOME, DBHelper.USUARIOS_EMAIL, DBHelper.USUARIOS_CEP,
                DBHelper.USUARIOS_BAIRRO, DBHelper.USUARIOS_RUA, DBHelper.USUARIOS_NUMERO, DBHelper.USUARIOS_COMPLEMENTO,
                DBHelper.EMPRESTIMOS_NOME_TABELA, DBHelper.EMPRESTIMOS_ID, DBHelper.EMPRESTIMOS_NOME_TABELA, DBHelper.USUARIOS_NOME_TABELA,
                DBHelper.EMPRESTIMOS_NOME_TABELA, DBHelper.USUARIOS_CPF, DBHelper.USUARIO_EMPRESTIMO, DBHelper.USUARIOS_CPF
        );

        System.out.println(sql);

        Cursor cursor = ler.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                String cpf = cursor.getString(cursor.getColumnIndexOrThrow("cpf"));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String cep = cursor.getString(cursor.getColumnIndexOrThrow("cep"));
                String bairro = cursor.getString(cursor.getColumnIndexOrThrow("bairro"));
                String rua = cursor.getString(cursor.getColumnIndexOrThrow("rua"));
                String numero = cursor.getString(cursor.getColumnIndexOrThrow("numero"));
                String complemento = cursor.getString(cursor.getColumnIndexOrThrow("complemento"));
                Integer emprestimos = cursor.getInt(cursor.getColumnIndexOrThrow("emprestimos"));

                Usuario usuario = new Usuario(cpf, nome, email, cep, bairro, rua, numero, complemento, emprestimos);
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usuarios;
    }

    public boolean atualizar(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", usuario.getNome());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("cep", usuario.getCep());
        contentValues.put("bairro", usuario.getBairro());
        contentValues.put("rua", usuario.getRua());
        contentValues.put("numero", usuario.getNumero());
        contentValues.put("complemento", usuario.getComplemento());


        try {
            String[] args = {usuario.getCpf()};
            escrever.update(DBHelper.USUARIOS_NOME_TABELA, contentValues, "cpf=?", args);
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletar(String cpf) {
        try {
            String[] args = {cpf};
            escrever.delete(DBHelper.USUARIOS_NOME_TABELA, "cpf=?", args);
        } catch (Exception e) {
            Log.i("INFO", "Erro apagar registro!" + e.getMessage());
            return false;
        }
        return true;
    }
}
