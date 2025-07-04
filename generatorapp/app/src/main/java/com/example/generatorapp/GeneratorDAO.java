package com.example.generatorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GeneratorDAO {
    private SQLiteDatabase db;

    public GeneratorDAO(Context context) {
        GeneratorDBHelper helper = new GeneratorDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Generator g) {
        ContentValues cv = new ContentValues();
        cv.put("model", g.getModelNumber());
        cv.put("currentA", g.getCurrentA());
        cv.put("currentB", g.getCurrentB());
        cv.put("currentC", g.getCurrentC());
        cv.put("FREQUENCY", g.getFrequency());

        db.insert("generator", null, cv);
    }

    public List<Generator> getAll() {
        List<Generator> list = new ArrayList<>();
        Cursor cursor = db.query("generator", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            list.add(new Generator(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("FREQUENCY"))));
        }
        cursor.close();
        return list;
    }

    public void update(Generator g) {
        ContentValues cv = new ContentValues();
        cv.put("model", g.getModelNumber());
        cv.put("currentA", g.getCurrentA());
        cv.put("currentB", g.getCurrentB());
        cv.put("currentC", g.getCurrentC());
        cv.put("FREQUENCY", g.getFrequency());
        db.update("generator", cv, "id=?", new String[]{String.valueOf(g.getId())});
    }

    public void delete(int id) {
        db.delete("generator", "id=?", new String[]{String.valueOf(id)});
    }

    public void close() {
        db.close();
    }
}

