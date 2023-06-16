package com.mertcansucu.mertcansucuobs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DuyurularHelper(context: Context) : SQLiteOpenHelper(context,"DYRLTS",null,1){
    override fun onCreate(db: SQLiteDatabase?){
        db?.execSQL("CREATE TABLE DUYURULISTETABLE(_id integer primary key autoincrement,DUYURUBASLIK TEXT,DUYURUKONU TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}