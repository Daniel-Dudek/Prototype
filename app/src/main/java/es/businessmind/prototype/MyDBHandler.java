package es.businessmind.prototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE_ITEM = "tituloproducto";
    public static final String COLUMN_DESCRIPTION_ITEM = "descripcion";
    public static final String COLUMN_PRICE = "precio";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = " CREATE TABLE " + TABLE_ITEMS + " ( " +COLUMN_ID +" TEXT, " + COLUMN_TITLE_ITEM + " TEXT, " +
                COLUMN_DESCRIPTION_ITEM + " TEXT, " + COLUMN_PRICE + " TEXT " + ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_ITEMS);
        onCreate(sqLiteDatabase);
    }

    public void addItem(Items item) {
        SQLiteDatabase db = getWritableDatabase();
        if(db!= null) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, item.getId());
            values.put(COLUMN_TITLE_ITEM, item.getTitleItem());
            values.put(COLUMN_DESCRIPTION_ITEM, item.getDescripcion());
            values.put(COLUMN_PRICE, item.getPrecio());
            db.insert(TABLE_ITEMS, null, values);
            db.close();
        }
    }

    public List<Items> mostrarItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM items", null);
        List<Items> item = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                item.add(new Items(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return item;
    }

    public void buscarItems(Items item, String titulo) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM items WHERE tituloproducto='"+titulo+"' ", null);
        if(cursor.moveToFirst()) {
            do {
                item.setDescripcion(cursor.getString(2));
                item.setPrecio(cursor.getString(3));
            } while (cursor.moveToNext());
        }
    }

    public void updateItem(String titulo, String descripcion, String precio) {
        SQLiteDatabase db = getWritableDatabase();
        if(db!= null) {
            db.execSQL("UPDATE items SET descripcion='"+descripcion+"',precio='"+precio+"' WHERE tituloproducto='"+titulo+"'");
            db.close();
        }
    }

    public void deleteItem(String titulo) {
        SQLiteDatabase db = getWritableDatabase();
        if(db!= null) {
            db.execSQL("DELETE FROM items WHERE tituloproducto='"+titulo+"' ");
            db.close();
        }
    }
}
