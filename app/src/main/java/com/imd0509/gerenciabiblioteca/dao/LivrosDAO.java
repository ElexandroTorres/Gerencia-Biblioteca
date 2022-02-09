package com.imd0509.gerenciabiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.imd0509.gerenciabiblioteca.helpers.DBHelper;
import com.imd0509.gerenciabiblioteca.model.Livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LivrosDAO {
    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public LivrosDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        write = dbHelper.getWritableDatabase();
        read = dbHelper.getReadableDatabase();
    }

    public boolean save(Livro livro) {
        ContentValues content = new ContentValues();
        content.put(DBHelper.LIVROS_TITULO, livro.getTitulo());
        content.put(DBHelper.LIVROS_DESCRICAO, livro.getDescricao());

        try {
            write.insert(DBHelper.LIVROS_NOME_TABELA, null, content);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Livro> listLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.LIVROS_NOME_TABELA + ";";
        Cursor cursor = read.rawQuery(sql, null);

        cursor.moveToFirst();
        while(cursor.moveToNext()) {
            Livro livro = new Livro();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_ID));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_TITULO));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_DESCRICAO));

            livro.setTitulo(titulo);
            livro.setDescricao(descricao);

            livros.add(livro);
        }
        cursor.close();

        return livros;
    }

    public boolean update(Livro livro) {
        ContentValues content = new ContentValues();
        content.put(DBHelper.LIVROS_ID, livro.getId());
        content.put(DBHelper.LIVROS_TITULO, livro.getTitulo());
        content.put(DBHelper.LIVROS_DESCRICAO, livro.getDescricao());

        try {
            String[] args = {Integer.toString(livro.getId())};
            write.update(DBHelper.LIVROS_NOME_TABELA, content, "id=?",args);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete(Livro livro) {
        try {
            String[] args = {Integer.toString(livro.getId())};
            write.delete(DBHelper.LIVROS_NOME_TABELA, "id=?", args);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteAll() {
        String sqlDelete = "DELETE FROM " + DBHelper.LIVROS_NOME_TABELA + ";";

        try {
            write.execSQL(sqlDelete);
            write.execSQL("VACUUM");
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
