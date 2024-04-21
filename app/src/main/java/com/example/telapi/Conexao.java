package com.example.telapi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    private static final String NAME = "banco.db";
    private static final int VERSION = 1;

    private static final String SQL_CREATE = "CREATE TABLE despesa (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "descricao VARCHAR(50), " +
            "valor DOUBLE, " +
            "vencimento DATE, " +
            "pago INTEGER DEFAULT 0);";

    public Conexao(@Nullable Context context){
        super(context, NAME, null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE);
    };
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

}
