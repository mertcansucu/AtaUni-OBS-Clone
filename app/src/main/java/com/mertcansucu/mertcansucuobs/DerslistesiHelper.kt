package com.mertcansucu.mertcansucuobs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DerslistesiHelper(context: Context) : SQLiteOpenHelper(context,"DLTS",null,1){
    override fun onCreate(db: SQLiteDatabase?){
        db?.execSQL("CREATE TABLE DERSLISTETABLE(_id integer primary key autoincrement,DERSADI TEXT,DERSKODU TEXT)")
        db?.execSQL("INSERT INTO DERSLISTETABLE(DERSADI,DERSKODU)VALUES('Programlamaya Giriş','PG101')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}