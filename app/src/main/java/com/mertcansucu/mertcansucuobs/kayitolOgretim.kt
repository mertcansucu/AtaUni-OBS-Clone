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
import androidx.appcompat.app.AlertDialog

class kayitolOgretim : AppCompatActivity() {
    lateinit var dbog : SQLiteDatabase
    lateinit var rsog : Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayitol_ogretim)
        val editTextTcKayitOgretim = findViewById<EditText>(R.id.editTextTcKayitOgretim)
        val editAdSoyadKayitOgretim = findViewById<EditText>(R.id.editAdSoyadKayitOgretim)
        val editTextTelefonKayitOgretim = findViewById<EditText>(R.id.editTextTelefonKayitOgretim)
        val editTextEmailKayitOgretim = findViewById<EditText>(R.id.editTextEmailKayitOgretim)
        val editTextUnvanKayitOgretim = findViewById<EditText>(R.id.editTextUnvanKayitOgretim)
        val buttonKayitYapOgretim = findViewById<Button>(R.id.buttonKayitYapOgretim)

        var ogretimGeciciHelper = OgretimGeciciHelper(applicationContext)
        dbog = ogretimGeciciHelper.readableDatabase
        rsog = dbog.rawQuery("SELECT * FROM OGRETIMGECICI",null)

       buttonKayitYapOgretim.setOnClickListener {
           var controlDeger1 : String
           controlDeger1 = "0"

           var sifre1 : String
           sifre1 = editTextTcKayitOgretim.text.toString()
           var cv1 = ContentValues()
           cv1.put("TCUYE",editTextTcKayitOgretim.text.toString())
           cv1.put("OGRTMADSYD",editAdSoyadKayitOgretim.text.toString())
           cv1.put("OGRTMTLFN",editTextTelefonKayitOgretim.text.toString())
           cv1.put("OGRTMEMAIL",editTextEmailKayitOgretim.text.toString())
           cv1.put("OGRTMSIFRE",sifre1)
           cv1.put("OGRTMUNVAN",editTextUnvanKayitOgretim.text.toString())
           cv1.put("BULL",controlDeger1)

           dbog.insert("OGRETIMGECICI",null,cv1)
           rsog.requery()

           var ad = AlertDialog.Builder(this)
           ad.setTitle("Yeni Kayıt")
           ad.setMessage("Kayıt Başarılı")
           ad.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
           })
           ad.show()
           intent = Intent(applicationContext,ogretim_giris::class.java)
           startActivity(intent)
       }
    }
}