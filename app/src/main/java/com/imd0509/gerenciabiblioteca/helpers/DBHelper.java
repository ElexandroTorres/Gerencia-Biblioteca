package com.imd0509.gerenciabiblioteca.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.imd0509.gerenciabiblioteca.model.Usuario;

public class DBHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "db_biblioteca";
    public static String LIVROS_NOME_TABELA = "livros";
    public static String LIVROS_ID = "id";
    public static String LIVROS_TITULO = "titulo";
    public static String LIVROS_DESCRICAO = "descricao";

    public static String EMPRESTIMOS_NOME_TABELA = "emprestimos";
    public static String EMPRESTIMOS_ID = "id";
    public static String NOME_EMPRESTIMO = "nome";
    public static String DATA_EMPRESTIMO = "data";
    public static String USUARIO_EMPRESTIMO = "usuario";
    public static String LIVRO_EMPRESTIMO = "livro";


    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + LIVROS_NOME_TABELA +
                "(" + LIVROS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                LIVROS_TITULO + " VARCHAR(50) NOT NULL, " +
                LIVROS_DESCRICAO + " VARCHAR(500) NOT NULL);";

        try {
            database.execSQL(sqlCreate);
        } catch (Exception e) {
            //TODO fazer algo aqui depois.
        }

        String sqlCreateEmprestimo = "CREATE TABLE IF NOT EXISTS " + EMPRESTIMOS_NOME_TABELA +
                "(" + EMPRESTIMOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                NOME_EMPRESTIMO + " VARCHAR(50) NOT NULL, " +
                DATA_EMPRESTIMO + " VARCHAR(50) NOT NULL, " +
                USUARIO_EMPRESTIMO + " VARCHAR(50) NOT NULL, " +
                LIVRO_EMPRESTIMO + " VARCHAR(50) NOT NULL);";

        try {
            database.execSQL(sqlCreateEmprestimo);
        }catch (Exception e){
            //TO DO
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + LIVROS_NOME_TABELA);
        database.execSQL("DROP TABLE IF EXISTS " + EMPRESTIMOS_NOME_TABELA);
        onCreate(database);
    }
}
