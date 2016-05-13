package com.example.asus.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button adddata= (Button) findViewById(R.id.add_data);

        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri =Uri.parse("content://com.example.asus.databasetest.provider/book");
                ContentValues values =new ContentValues();
                values.put("name","a clash of king");
                values.put("author","georgoe martin");
                values.put("price",22.85);
                 Uri newuri =getContentResolver().insert(uri,values);
                 newId = newuri.getPathSegments().get(1);

            }
        });
        Button querydata = (Button) findViewById(R.id.query_data);
        querydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri =Uri.parse("content://com.example.asus.databasetest.provider/book");
                Cursor cursor =getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null)
                {
                    while (cursor.moveToNext())
                    {
                        String name= cursor.getString(cursor.getColumnIndex("name"));
                        String author =cursor.getString(cursor.getColumnIndex("author"));
                        double price =cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("mainactivity","book name is "+name);
                        Log.d("mainactivity","author is"+author);
                        Log.d("mainactivity","price is "+price);
                    }
                    cursor.close();
                }
                Log.d("mainactivity","lalalalalalal");
            }
        });
        Button updatadata =(Button)findViewById(R.id.update_data);
        updatadata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri =Uri.parse("content://com.example.asus.databasetest.provider"+newId);
                ContentValues values=new ContentValues();
                values.put("name","a storm of swords");
                values.put("price",24.05);
                getContentResolver().update(uri,values,null,null);

            }
        });
        Button deletedate =(Button)findViewById(R.id.delete_Data);
        deletedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri =Uri.parse("content://com.example.asus.databasetest.provider"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
