package com.mertcansucu.mertcansucuobs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OgrenciGeciciHelper(context: Context) : SQLiteOpenHelper(context,"OGRNCGCHLPR",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE OGRENCIGECICI(_id integer primary key autoincrement,TCUYE TEXT,OGRNCMADSYD TEXT,OGRNCTLFN TEXT,OGRNCEMAIL TEXT,OGRNCSIFRE TEXT,OGRNCUNVAN TEXT,BUL TEXT)")
        db?.execSQL("INSERT INTO OGRENCIGECICI(TCUYE,OGRNCMADSYD,OGRNCTLFN,OGRNCEMAIL,OGRNCSIFRE,OGRNCUNVAN,BUL)VALUES('14483091820','MERT CAN SUCU','05454414690','mrtcnscc@gmail.com','14483091820','Öğrenci','0')")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


}