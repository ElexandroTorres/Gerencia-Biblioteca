package com.imd0509.gerenciabiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.imd0509.gerenciabiblioteca.helpers.DBHelper;
import com.imd0509.gerenciabiblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivrosDAO {
    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public LivrosDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        write = dbHelper.getWritableDatabase();
        read = dbHelper.getReadableDatabase();
    }

    public long save(Livro livro) {
        ContentValues content = new ContentValues();
        content.put(DBHelper.LIVROS_TITULO, livro.getTitulo());
        content.put(DBHelper.LIVROS_DESCRICAO, livro.getDescricao());
        content.put(DBHelper.LIVROS_AUTORES, livro.getAutores());
        content.put(DBHelper.LIVROS_PUBLICADORA_ANO, livro.getPublicadoraAno());
        content.put(DBHelper.LIVROS_URL_IMAGEM, livro.getUrlImagemCapa());
        content.put(DBHelper.LIVROS_DISPONIBILIDADE, livro.getDisponibilidade());

        long id;

        try {
            id = write.insert(DBHelper.LIVROS_NOME_TABELA, null, content);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return id;
    }

    public Livro getLivro(int id) {
        Livro livro = new Livro();
        String sql = "SELECT * FROM " + DBHelper.LIVROS_NOME_TABELA + " WHERE " + DBHelper.LIVROS_ID + "='" + id + "'";
        Cursor cursor = read.rawQuery(sql, null);

        cursor.moveToFirst();

        if (cursor.getCount() == 1) {
            return livro;
        }

        return null;

    }

    public List<Livro> listLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.LIVROS_NOME_TABELA + ";";
        Cursor cursor = read.rawQuery(sql, null);


        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Livro livro = new Livro();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_ID));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_TITULO));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_DESCRICAO));
            String autores = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_AUTORES));
            String publicadoraAno = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_PUBLICADORA_ANO));
            String urlImagem = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_URL_IMAGEM));
            String disponibilidade = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_DISPONIBILIDADE));

            livro.setId(id);
            livro.setTitulo(titulo);
            livro.setDescricao(descricao);
            livro.setAutores(autores);
            livro.setPublicadoraAno(publicadoraAno);
            livro.setUrlImagemCapa(urlImagem);
            livro.setDisponibildiade(disponibilidade);

            livros.add(livro);
        }
        cursor.close();


        return livros;
    }

    public boolean update(Livro livro) {
        ContentValues content = new ContentValues();
        content.put(DBHelper.LIVROS_TITULO, livro.getTitulo());
        content.put(DBHelper.LIVROS_DESCRICAO, livro.getDescricao());
        content.put(DBHelper.LIVROS_AUTORES, livro.getAutores());
        content.put(DBHelper.LIVROS_PUBLICADORA_ANO, livro.getPublicadoraAno());
        content.put(DBHelper.LIVROS_URL_IMAGEM, livro.getUrlImagemCapa());
        content.put(DBHelper.LIVROS_DISPONIBILIDADE, livro.getDisponibilidade());

        try {
            String[] args = {Integer.toString(livro.getId())};
            write.update(DBHelper.LIVROS_NOME_TABELA, content, "id=?", args);
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
