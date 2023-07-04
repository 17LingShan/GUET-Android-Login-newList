package com.example.code07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<News> newlist = new ArrayList<>();
    MySQLiteOpenHelper myDbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);
        myDbHelper = new MySQLiteOpenHelper(MainActivity.this);
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                NewsContract.NewsEntry.TABLE_NAME,
                null, null, null, null, null, null);

//        initData();
        int titleIndex = cursor.getColumnIndex(
                NewsContract.NewsEntry.COLUMN_NAME_TITLE);
        int authorIndex = cursor.getColumnIndex(
                NewsContract.NewsEntry.COLUMN_NAME_AUTHOR);
        int imageIndex = cursor.getColumnIndex(
                NewsContract.NewsEntry.COLUMN_NAME_IMAGE);

        while (cursor.moveToNext()) {
            News news = new News();

            String title = cursor.getString(titleIndex);
            String author = cursor.getString(authorIndex);
            String image = cursor.getString(imageIndex);

            Bitmap bitmap = BitmapFactory.decodeStream(
                    getClass().getResourceAsStream("/" + image));

            news.setTitle(title);
            news.setAuthor(author);
            news.setImage(bitmap);
            newlist.add(news);
        }
        NewsAdapter newsAdapter = new NewsAdapter(
                MainActivity.this,
                R.layout.list_item,
                newlist);
        ListView lvNewsList = findViewById(R.id.lv_news_list);
        lvNewsList.setAdapter(newsAdapter);
    }

    private void initData() {
        int length;

        String[] titles = getResources().getStringArray(R.array.titles);
        String[] authors = getResources().getStringArray(R.array.authors);
        TypedArray images = getResources().obtainTypedArray(R.array.images);

        if (titles.length > authors.length) {
            length = authors.length;
        } else {
            length = titles.length;
        }

        for (int i = 0; i < length; i++) {
            News news = new News();
            news.setTitle(titles[i]);
            news.setAuthor(authors[i]);
            news.setImageId(images.getResourceId(i, 0));

            newlist.add(news);
        }
    }
}