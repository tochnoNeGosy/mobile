package com.example.rightmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookWork.db";
    private static final String TABLE_NAME = "book";
    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_AUTHOR = "author";
    private static final String COLUMN_NAME_GENRE = "genre";
    private static final String COLUMN_NAME_QUANTITY = "quantity";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_AUTHOR + " TEXT," +
                    COLUMN_NAME_GENRE + " TEXT," +
                    COLUMN_NAME_QUANTITY + " INTEGER)";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //asd
    }

    public void AddBook(Book book){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_AUTHOR, book.getAuthor());
        values.put(COLUMN_NAME_GENRE, book.getGenre());
        values.put(COLUMN_NAME_QUANTITY, book.getQuantity());
        long newRowId = db.insert(TABLE_NAME, null, values);
    }

    public void UpdateBook(Book book){
        SQLiteDatabase db = getWritableDatabase();
        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_AUTHOR, book.getAuthor());
        values.put(COLUMN_NAME_GENRE, book.getGenre());
        values.put(COLUMN_NAME_QUANTITY, book.getQuantity());

        String selection = COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = { String.valueOf(book.getId()) };

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public List<Book> GetAllBooks(){
        SQLiteDatabase db = getWritableDatabase();
        String[] projection = {
                COLUMN_NAME_ID,
                COLUMN_NAME_AUTHOR,
                COLUMN_NAME_GENRE,
                COLUMN_NAME_QUANTITY
        };

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List<Book> books = new ArrayList<Book>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_ID));
            String author = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_AUTHOR));
            String genre = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_GENRE));
            int quantity = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
            books.add(new Book(id, author, genre, quantity));
        }
        cursor.close();

        return books;
    }

    public List<Book> FilterBookByGenre(String genre){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                COLUMN_NAME_ID,
                COLUMN_NAME_AUTHOR,
                COLUMN_NAME_GENRE,
                COLUMN_NAME_QUANTITY
        };

        String selection = COLUMN_NAME_GENRE + " = ?";
        String[] selectionArgs = { genre };


        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        List<Book> books = new ArrayList<Book>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_ID));
            String author = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_AUTHOR));
            String genreBook = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_GENRE));
            int quantity = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME_QUANTITY));
            books.add(new Book(id, author, genreBook, quantity));
        }
        cursor.close();

        return books;
    }
}
