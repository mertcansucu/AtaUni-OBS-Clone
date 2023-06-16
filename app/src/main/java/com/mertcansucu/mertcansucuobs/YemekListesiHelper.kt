package com.mertcansucu.mertcansucuobs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class YemekListesiHelper(context: Context) : SQLiteOpenHelper(context,"YMKLTS",null,1){
    override fun onCreate(db: SQLiteDatabase?){
        db?.execSQL("CREATE TABLE YEMEKLISTETABLE(_id integer primary key autoincrement,YEMEK TEXT,TARIH TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}