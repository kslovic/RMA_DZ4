package com.example.kslovic.bugsy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NewsDBHelper extends SQLiteOpenHelper {
    // Singleton
    private static NewsDBHelper mNewsDBHelper = null;
    private NewsDBHelper (Context context){
        super(context.getApplicationContext(),Schema.DATABASE_NAME,null,Schema.SCHEMA_VERSION);
    }
    public static synchronized NewsDBHelper getInstance(Context context){
        if(mNewsDBHelper == null){
            mNewsDBHelper = new NewsDBHelper(context);
        }
        return mNewsDBHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE_MY_NEWS); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_MY_NEWS);
        this.onCreate(db);
    }

    static final String CREATE_TABLE_MY_NEWS = "CREATE TABLE " + Schema.TABLE_MY_NEWS +
            " (" + Schema.TITLE + " TEXT," + Schema.PUBDATE + " TEXT,"+ Schema.CATEGORY + " TEXT,"+ Schema.DESCRIPTION + " TEXT,"+ Schema.IMAGEURL + " TEXT,"+ Schema.LINK + " TEXT);";
    static final String DROP_TABLE_MY_NEWS = "DROP TABLE IF EXISTS " + Schema.TABLE_MY_NEWS;
    static final String SELECT_ALL_NEWS = "SELECT * FROM " + Schema.TABLE_MY_NEWS;

    public void insertNews(News news){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.TITLE, news.getTitle());
        contentValues.put(Schema.PUBDATE, news.getPubDate());
        contentValues.put(Schema.CATEGORY, news.getCategory());
        contentValues.put(Schema.DESCRIPTION, news.getDescription());
        contentValues.put(Schema.IMAGEURL, news.getImageUrl());
        contentValues.put(Schema.LINK, news.getLink());

        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        writeableDatabase.insert(Schema.TABLE_MY_NEWS, Schema.TITLE,contentValues);
        writeableDatabase.close();
    }
    public ArrayList<News> getNews(String etCategory) {
        String SELECT_NEWS = "SELECT * FROM " + Schema.TABLE_MY_NEWS + " WHERE " + Schema.CATEGORY + " LIKE " + "'%" + etCategory + "%'";
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        Cursor newsCursor = writeableDatabase.rawQuery(SELECT_NEWS,null);
        ArrayList<News> news = new ArrayList<>();
        if(newsCursor.moveToFirst()){
            do{
                String title = newsCursor.getString(0);
                String pubDate = newsCursor.getString(1);
                String category = newsCursor.getString(2);
                String description = newsCursor.getString(3);
                String imageUrl = newsCursor.getString(4);
                String link = newsCursor.getString(5);
                news.add(new News(title, pubDate, category, description, imageUrl, link));
            }while(newsCursor.moveToNext());
        }
        newsCursor.close();
        writeableDatabase.close();
        return news;
    }
        public ArrayList<News> getAllNews(){
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        Cursor newsCursor = writeableDatabase.rawQuery(SELECT_ALL_NEWS,null);
        ArrayList<News> news = new ArrayList<>();
        if(newsCursor.moveToFirst()){
            do{
                String title = newsCursor.getString(0);
                String pubDate = newsCursor.getString(1);
                String category = newsCursor.getString(2);
                String description = newsCursor.getString(3);
                String imageUrl = newsCursor.getString(4);
                String link = newsCursor.getString(5);
                news.add(new News(title, pubDate, category, description, imageUrl, link));
            }while(newsCursor.moveToNext());
        }
        newsCursor.close();
        writeableDatabase.close();
        return news;
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Schema.TABLE_MY_NEWS, null, null);
    }
    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "news.db";
        //A table to store owned books:
        static final String TABLE_MY_NEWS = "my_news";
        static final String TITLE = "title";
        static final String PUBDATE = "pubDate";
        static final String CATEGORY = "category";
        static final String DESCRIPTION = "description";
        static final String IMAGEURL = "imageUrl";
        static final String LINK = "link";


    }
}
