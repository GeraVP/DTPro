package com.example.dt.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelp(context: Context): SQLiteOpenHelper(context,MyDbNameClss.DATABASE_NAME,null,MyDbNameClss.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyDbNameClss.CT)
    } // Создание БД

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyDbNameClss.SQL_DELETE_TABLE)
        onCreate(db)
    } // Обновление БД

}