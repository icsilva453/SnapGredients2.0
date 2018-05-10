package mycontentprovider.edu.csulb.nav;

/**
 * Created by Isaac S on 5/7/2018.
 */

import android.database.Cursor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class InglistActivity extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spinnerExample";
    private static final String TABLE_LABELS = "labels";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    public InglistActivity(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_LABELS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABELS);
        onCreate(db);
    }
    public void insertLabel(String label){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,label);
        db.insert(TABLE_LABELS, null, values);
        db.close();
    }
    public List<String>getAllLabels(){
        List<String> labels = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LABELS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{
                labels.add(cursor.getString(1));
            } 
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return labels;
    }
    SQLiteDatabase mdb;
    public boolean delete (String n){
       // id = DATABASE_NAME;
return mdb.delete(TABLE_LABELS,KEY_NAME + "= " + "name" + "'",null) > 0;

    }
}

