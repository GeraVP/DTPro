package com.example.dt.db
import android.provider.BaseColumns
object MyDbNameClss : BaseColumns
{
    const val TABLE_NAME = "my_table"  // 0 title content
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyLessonDb.db"

    const val CT = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE TEXT,$COLUMN_NAME_CONTENT TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}