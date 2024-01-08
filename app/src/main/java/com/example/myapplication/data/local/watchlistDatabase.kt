package com.example.myapplication.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SampleSQLiteDBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS " + COMPANY_ELEMENT + " (" +
                    ELEMENT_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " + ")"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COMPANY_ELEMENT)
        onCreate(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "WatchlistDB"
        const val COMPANY_ELEMENT = "WatchListElement"
        const val ELEMENT_ID = "id"
    }

    fun addWatchlistElementToDB(sqLiteDatabase: SQLiteDatabase, element_API_Index: String)
    {
        sqLiteDatabase.execSQL(" INSERT INTO COMPANY_ELEMENT(ELEMENT_ID) VALUES (element_API_Index)")
    }
}