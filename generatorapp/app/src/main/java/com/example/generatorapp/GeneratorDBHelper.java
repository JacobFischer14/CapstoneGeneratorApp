package com.example.generatorapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GeneratorDBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "generator.db";
    private static final int DB_VERSION = 2;

    public GeneratorDBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE generator (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "model TEXT," +
                "currentA TEXT, currentB TEXT, currentC TEXT," +
                "FREQUENCY TEXT," +
                "waveformA TEXT, waveformB TEXT, waveformC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS generator");
        onCreate(db);
    }
}

