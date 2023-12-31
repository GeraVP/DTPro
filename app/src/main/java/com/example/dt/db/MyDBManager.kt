package com.example.dt.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDBManager(val context: Context) {
    val myDBHelp = MyDBHelp(context) // Будет открывать БД
    var db: SQLiteDatabase? = null

    fun openDb()
    {
        db = myDBHelp.writableDatabase // Чтобы бд открылась для записи и считывания
    }
    fun insertToDb(title: String,content:String)
    {
        val values = ContentValues().apply {
            put(MyDbNameClss.COLUMN_NAME_TITLE,title)
            put(MyDbNameClss.COLUMN_NAME_CONTENT,content)
        }
        db?.insert(MyDbNameClss.TABLE_NAME,null,values)
    }
    fun readDbData(): ArrayList<String>{
        val dataList = ArrayList<String>() // Создал массив
        val cursor = db?.query(MyDbNameClss.TABLE_NAME,null,null,null,null,null,null)

              while (cursor?.moveToNext()!!)
              {
                  val dataT = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClss.COLUMN_NAME_TITLE))
                  dataList.add(dataT.toString())
                 //val dataText = cursor?.getString(cursor.getColumnIndex(MyDbNameClss.COLUMN_NAME_TITLE))
              }

        // cursor - спец класс для считывания данных
        cursor.close()
        return dataList // Возвращаю его
    } // Возвращает массив типа string
    fun closeDb(){
        myDBHelp.close()
    }

}