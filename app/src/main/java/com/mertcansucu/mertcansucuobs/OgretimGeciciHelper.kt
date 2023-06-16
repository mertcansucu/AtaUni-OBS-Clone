package com.mertcansucu.mertcansucuobs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OgretimGeciciHelper(context: Context) : SQLiteOpenHelper(context,"OGCHLPR",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE OGRETIMGECICI(_id integer primary key autoincrement,TCUYE TEXT,OGRTMADSYD TEXT,OGRTMTLFN TEXT,OGRTMEMAIL TEXT,OGRTMSIFRE TEXT,OGRTMUNVAN TEXT,BULL TEXT)")
        db?.execSQL("INSERT INTO OGRETIMGECICI(TCUYE,OGRTMADSYD,OGRTMTLFN,OGRTMEMAIL,OGRTMSIFRE,OGRTMUNVAN,BULL)VALUES('11111111111','FERHAT BOZKURT','05436440364','ferhat@gmail.com','11111111111','Öğretim Üyesi','0')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}