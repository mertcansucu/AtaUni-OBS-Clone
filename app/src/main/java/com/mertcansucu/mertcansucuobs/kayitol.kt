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

class kayitol : AppCompatActivity() {
    lateinit var dba : SQLiteDatabase
    lateinit var rsaa : Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayitol)
        val editTextTcKayit = findViewById<EditText>(R.id.editTextTcKayit)
        val editAdSoyadKayit = findViewById<EditText>(R.id.editAdSoyadKayit)
        val editTextTelefonKayit = findViewById<EditText>(R.id.editTextTelefonKayit)
        val editTextEmailKayit = findViewById<EditText>(R.id.editTextEmailKayit)
        val editTextUnvanKayit = findViewById<EditText>(R.id.editTextUnvanKayit)
        val buttonKayitYap = findViewById<Button>(R.id.buttonKayitYap)

        var ogrenciGeciciHelper = OgrenciGeciciHelper(applicationContext)
        dba = ogrenciGeciciHelper.readableDatabase
        rsaa = dba.rawQuery("SELECT * FROM OGRENCIGECICI",null)

        buttonKayitYap.setOnClickListener {
            var controlDeger : String
            controlDeger = "0"

            var sifre : String
            sifre = editTextTcKayit.text.toString()

            var cv = ContentValues()
            cv.put("TCUYE",editTextTcKayit.text.toString())
            cv.put("OGRNCMADSYD",editAdSoyadKayit.text.toString())
            cv.put("OGRNCTLFN",editTextTelefonKayit.text.toString())
            cv.put("OGRNCEMAIL",editTextEmailKayit.text.toString())
            cv.put("OGRNCSIFRE",sifre)
            cv.put("OGRNCUNVAN",editTextUnvanKayit.text.toString())
            cv.put("BUL",controlDeger)

            dba.insert("OGRENCIGECICI",null,cv)
            rsaa.requery()

            var ad = AlertDialog.Builder(this)
            ad.setTitle("Yeni Kayıt")
            ad.setMessage("Kayıt Başarılı")
            ad.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            })
            ad.show()
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}