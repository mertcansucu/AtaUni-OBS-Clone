package com.mertcansucu.mertcansucuobs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class YoneticiHelperr(context: Context) : SQLiteOpenHelper(context,"YNTCC",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE YONETICITBL(_id integer primary key autoincrement,TC TEXT,YNTCSIFRE TEXT)")
        db?.execSQL("INSERT INTO YONETICITBL(TC,YNTCSIFRE)VALUES('11111111111','11111111111')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}