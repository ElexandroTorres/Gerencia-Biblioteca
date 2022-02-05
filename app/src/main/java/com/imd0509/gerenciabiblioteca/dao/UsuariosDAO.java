package com.imd0509.gerenciabiblioteca.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.imd0509.gerenciabiblioteca.helpers.DBHelper;
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
        contentValues.put("nome", usuario.getNome());

        try {
            escrever.insert(DBHelper.USUARIOS_NOME_TABELA, null, contentValues);
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
            return false;
        }
        return true;
    }

    public List<Usuario> listar() throws ParseException {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.USUARIOS_NOME_TABELA + ";";

        Cursor cursor = ler.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));

                Usuario usuario = new Usuario(id, nome, new ArrayList<>());
                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return usuarios;
    }

    public boolean update(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", usuario.getNome());

        try {
            String[] args = {usuario.getId().toString()};
            escrever.update(DBHelper.USUARIOS_NOME_TABELA, contentValues, "id=?", args);
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
            return false;
        }
        return true;
    }
}
