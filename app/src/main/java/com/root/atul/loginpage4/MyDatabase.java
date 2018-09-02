package com.root.atul.loginpage4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper
{


    public static String DB_NAME="MYAPP";
    public static int VERSION_NAME=101;
    public Context context;
    public static int  count=0;
    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDatabase(Context context)
    {
        super(context,DB_NAME,null,VERSION_NAME);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        String qry="create table employee(count number)";
        sqLiteDatabase.execSQL(qry);
        count=1;
        Toast.makeText(context,"table created ",Toast.LENGTH_SHORT).show();
    }


    public void insert()
    {
        String qry="insert into employee values("+count+")";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(qry);
        Toast.makeText(context,"data inserted value of count is"+count,Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
