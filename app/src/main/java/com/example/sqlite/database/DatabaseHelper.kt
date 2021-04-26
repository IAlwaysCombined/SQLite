package com.example.sqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite.utilite.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "people.db", null, 1) {

    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT, EMAIL TEXT, TVSHOW TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }
    //Create new entry
    fun addData(name:String?, email:String?) : Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_USER, name)
        contentValues.put(EMAIL_USER, email)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }
    //Receipt all entry
    fun showData(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    //Change entry
    fun reAddData(id: String?, name: String?, email: String?){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_USER, name)
        contentValues.put(EMAIL_USER, email)
        db.update(TABLE_NAME, contentValues, "$ID_USER= $id", null)
    }
    //Delete entry
    fun deleteData(id:String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ID_USER= $id", null)
    }
}