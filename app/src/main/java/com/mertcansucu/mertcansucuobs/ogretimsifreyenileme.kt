package com.mertcansucu.mertcansucuobs

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ogretimsifreyenileme : AppCompatActivity() {
    lateinit var dba2: SQLiteDatabase
    lateinit var rsaa2: Cursor
    private var atc:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ogretimsifreyenileme)

        atc= intent.getStringExtra("atc").toString()

        val ogretimsifreguncellemebutonu = findViewById<Button>(R.id.ogretimsifreguncellemebutonu)
        val editTextYeniSifreOgretim = findViewById<EditText>(R.id.editTextYeniSifreOgretim)

        var ogretimGeciciHelper = OgretimGeciciHelper(applicationContext)
        dba2 = ogretimGeciciHelper.readableDatabase
        rsaa2 = dba2.rawQuery("SELECT * FROM OGRETIMGECICI", null)

        ogretimsifreguncellemebutonu.setOnClickListener {
            var cvo = ContentValues()
            cvo.put("OGRTMSIFRE",editTextYeniSifreOgretim.text.toString())
            dba2.update("OGRETIMGECICI",cvo,"TCUYE = ?", arrayOf(atc))
            rsaa2.requery()

            Toast.makeText(applicationContext,"Güncelleme Başarılı", Toast.LENGTH_LONG).show()
            intent = Intent(applicationContext,ogretim_giris::class.java)
            startActivity(intent)
        }



    }
}