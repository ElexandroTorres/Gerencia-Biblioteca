package com.imd0509.gerenciabiblioteca.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "db_biblioteca";
    public static String LIVROS_NOME_TABELA = "livros";
    public static String LIVROS_ID = "id";
    public static String LIVROS_TITULO = "titulo";
    public static String LIVROS_DESCRICAO = "descricao";
    public static String USUARIOS_NOME_TABELA = "usuarios";
    public static String USUARIOS_ID = "id";
    public static String USUARIOS_NOME = "nome";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sqlCreateLivros = "CREATE TABLE IF NOT EXISTS " + LIVROS_NOME_TABELA +
                "(" + LIVROS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                LIVROS_TITULO + " VARCHAR(50) NOT NULL, " +
                LIVROS_DESCRICAO + " VARCHAR(500) NOT NULL);";

        String sqlCreateUsuarios = "CREATE TABLE IF NOT EXISTS " + USUARIOS_NOME_TABELA +
                "(" + USUARIOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                USUARIOS_NOME + " VARCHAR(50) NOT NULL);";

        try {
            database.execSQL(sqlCreateLivros);
            database.execSQL(sqlCreateUsuarios);
        } catch (Exception e) {
            //TODO fazer algo aqui depois.
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + LIVROS_NOME_TABELA);
        database.execSQL("DROP TABLE IF EXISTS " + USUARIOS_NOME_TABELA);
        onCreate(database);
    }
}
