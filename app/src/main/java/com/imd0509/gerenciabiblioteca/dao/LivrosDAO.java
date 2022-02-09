package com.imd0509.gerenciabiblioteca.dao;

package com.elexandro.minhasanotacoes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.imd0509.gerenciabiblioteca.helpers.DBHelper;
import com.imd0509.gerenciabiblioteca.model.Livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LivrosDado {
    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public NotesDAO(Context context) {
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

    public List<Livro> listNotes() {
        List<Livro> notes = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.LIVROS_NOME_TABELA + ";";
        Cursor cursor = read.rawQuery(sql, null);

        cursor.moveToFirst();
        while(cursor.moveToNext()) {

            Livro livro = new Livro();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_TITULO));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.LIVROS_DESCRICAO));

            note.setId(id);
            note.setTitle(title);
            note.setDescription(description);
            note.setDate(date);

            notes.add(note);
        }
        cursor.close();
        Collections.sort(notes, Collections.reverseOrder());
        return notes;
    }

    public boolean update(Note note) {
        ContentValues content = new ContentValues();
        content.put("title", note.getTitle());
        content.put("description", note.getDescription());
        content.put("date", note.getDate());

        try {
            String[] args = {Integer.toString(note.getId())};
            write.update(DBHelper.TABLE_NAME, content, "id=?",args);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean delete(Note note) {
        try {
            String[] args = {Integer.toString(note.getId())};
            write.delete(DBHelper.TABLE_NAME, "id=?", args);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteAll() {
        String sqlDelete = "DELETE FROM " + DBHelper.TABLE_NAME + ";";

        try {
            write.execSQL(sqlDelete);
            write.execSQL("VACUUM");
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
