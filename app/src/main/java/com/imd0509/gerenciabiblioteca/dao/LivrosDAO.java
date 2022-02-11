package com.imd0509.gerenciabiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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

        Log.d("banco", livro.getTitulo());
        Log.d("banco", livro.getDescricao());
        Log.d("banco", livro.getAutores());
        Log.d("banco", livro.getPublicadoraAno());
        Log.d("banco", livro.getUrlImagemCapa());

        long id;

        try {
            id = write.insert(DBHelper.LIVROS_NOME_TABELA, null, content);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return id;
    }

    public List<Livro> listLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.LIVROS_NOME_TABELA + ";";
        Cursor cursor = read.rawQuery(sql, null);

        Log.d("cursor", "" + cursor.getCount());

        cursor.moveToFirst();
        while(cursor.moveToNext()) {
            Log.d("teste", "entrou no while");
            Livro livro = new Livro();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_ID));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_TITULO));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_DESCRICAO));
            String autores = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_AUTORES));
            String publicadoraAno = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_PUBLICADORA_ANO));
            String urlImagem = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_URL_IMAGEM));

            livro.setId(id);
            livro.setTitulo(titulo);
            livro.setDescricao(descricao);
            livro.setAutores(autores);
            livro.setPublicadoraAno(publicadoraAno);
            livro.setUrlImagemCapa(urlImagem);

            livros.add(livro);
        }
        cursor.close();

        Log.d("teste", "saiu do while");

        return livros;
    }

    public boolean update(Livro livro) {
        ContentValues content = new ContentValues();
        content.put(DBHelper.LIVROS_TITULO, livro.getTitulo());
        content.put(DBHelper.LIVROS_DESCRICAO, livro.getDescricao());
        content.put(DBHelper.LIVROS_AUTORES, livro.getAutores());
        content.put(DBHelper.LIVROS_PUBLICADORA_ANO, livro.getPublicadoraAno());
        content.put(DBHelper.LIVROS_URL_IMAGEM, livro.getUrlImagemCapa());

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
