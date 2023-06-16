package com.mertcansucu.mertcansucuobs

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class yonetici_giris : AppCompatActivity() {
    lateinit var db : SQLiteDatabase
    lateinit var rsa: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetici_giris)

        //buttonOgrenciGitt buttonOgretimGitt buttonYoneticiGitt
        val editTextTcYonetıcı = findViewById<EditText>(R.id.editTextTcYonetıcı)
        val editTextPasswordYonetici = findViewById<EditText>(R.id.editTextPasswordYonetici)
        val buttonGirisYonetici = findViewById<Button>(R.id.buttonGirisYoneticiA)
        val buttonOgrenciGitt = findViewById<Button>(R.id.buttonOgrenciGitt)
        val buttonOgretimGitt = findViewById<Button>(R.id.buttonOgretimGitt)
        val buttonYoneticiGitt = findViewById<Button>(R.id.buttonYoneticiGitt)

        var yoneticiHelperr = YoneticiHelperr(applicationContext)
        db = yoneticiHelperr.readableDatabase

        buttonOgrenciGitt.setOnClickListener {
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }

        buttonOgretimGitt.setOnClickListener {
            intent = Intent(applicationContext,ogretim_giris::class.java)
            startActivity(intent)
        }

        buttonYoneticiGitt.setOnClickListener {
            intent = Intent(applicationContext,yonetici_giris::class.java)
            startActivity(intent)
        }
        buttonGirisYonetici.setOnClickListener {
            var args = listOf<String>(editTextTcYonetıcı.text.toString(),editTextPasswordYonetici.text.toString()).toTypedArray()
            rsa = db.rawQuery("SELECT * FROM YONETICITBL WHERE TC = ? AND YNTCSIFRE = ?",args)

            if(rsa.moveToNext()){
                Toast.makeText(applicationContext,"Giriş Başarılı",Toast.LENGTH_LONG).show()
                intent = Intent(applicationContext,yoneticiekrani::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"Giriş Başarısız",Toast.LENGTH_LONG).show()

            }
        }
    }
}