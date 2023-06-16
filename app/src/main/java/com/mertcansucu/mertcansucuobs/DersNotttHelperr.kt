package com.mertcansucu.mertcansucuobs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DersNotttHelperr(context: Context) : SQLiteOpenHelper(context,"DLTSNT",null,1){
    override fun onCreate(db: SQLiteDatabase?){
        db?.execSQL("CREATE TABLE DERSLISTENOTTABLE(_id integer primary key autoincrement,TCUYE TEXT,DERSADI TEXT,DERSHARFNOT TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}