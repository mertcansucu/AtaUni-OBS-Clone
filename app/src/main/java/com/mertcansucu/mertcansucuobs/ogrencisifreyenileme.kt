package com.mertcansucu.mertcansucuobs

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class ogrencisifreyenileme : AppCompatActivity() {
    lateinit var dba1: SQLiteDatabase
    lateinit var rsaa1: Cursor
    private var tc:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ogrencisifreyenileme)

        tc= intent.getStringExtra("tc").toString()

        val sifreguncellemebutonu = findViewById<Button>(R.id.sifreguncellemebutonu)
        val editTextYeniSifre = findViewById<EditText>(R.id.editTextYeniSifre)

        var ogrenciGeciciHelper = OgrenciGeciciHelper(applicationContext)
        dba1 = ogrenciGeciciHelper.readableDatabase
        rsaa1 = dba1.rawQuery("SELECT * FROM OGRENCIGECICI", null)

        sifreguncellemebutonu.setOnClickListener {
            var cvv = ContentValues()
            cvv.put("OGRNCSIFRE", editTextYeniSifre.text.toString())
            dba1.update("OGRENCIGECICI", cvv, "TCUYE = ?", arrayOf(tc))
            rsaa1.requery()

            Toast.makeText(applicationContext,"Guncelleme Basarili", Toast.LENGTH_LONG).show()
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}