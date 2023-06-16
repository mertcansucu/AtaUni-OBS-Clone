package com.mertcansucu.mertcansucuobs

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ogretim_giris : AppCompatActivity() {
    lateinit var dba1 : SQLiteDatabase
    lateinit var rsaa1 : Cursor
    lateinit var rsaaa1: Cursor

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ogretim_giris)

        sharedPreferences=this.getSharedPreferences("Info", MODE_PRIVATE)
        editor=sharedPreferences.edit()

        val buttonGirisOgretim = findViewById<Button>(R.id.buttonGirisOgretim)
        val buttonOgrenciOgretim = findViewById<Button>(R.id.buttonOgrenciOgretim)
        val buttonOgretimGit = findViewById<Button>(R.id.buttonOgretimGit)
        val buttonYoneticiOgretim = findViewById<Button>(R.id.buttonYoneticiOgretim)
        val editTextTcOgretim = findViewById<EditText>(R.id.editTextTcOgretim)
        val editTextPasswordOgretim = findViewById<EditText>(R.id.editTextPasswordOgretim)
        val buttonKayitOgretim = findViewById<Button>(R.id.buttonKayitOgretim)

        var ogretimGeciciHelper = OgretimGeciciHelper(applicationContext)
        dba1 = ogretimGeciciHelper.readableDatabase

        buttonOgrenciOgretim.setOnClickListener {
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }

        buttonOgretimGit.setOnClickListener {
            intent = Intent(applicationContext,ogretim_giris::class.java)
            startActivity(intent)
        }

        buttonYoneticiOgretim.setOnClickListener {
            intent = Intent(applicationContext,yonetici_giris::class.java)
            startActivity(intent)
        }

        buttonKayitOgretim.setOnClickListener {
            intent = Intent(applicationContext,kayitolOgretim::class.java)
            startActivity(intent)
        }

        buttonGirisOgretim.setOnClickListener {
            var yeniControl1 : String
            yeniControl1 = "1"

            var argsss1 = listOf<String>(yeniControl1).toTypedArray()
            var argss1 = listOf<String>(editTextTcOgretim.text.toString(),editTextPasswordOgretim.text.toString(),"1").toTypedArray()

            rsaa1 = dba1.rawQuery("SELECT * FROM OGRETIMGECICI WHERE TCUYE = ? AND OGRTMSIFRE = ? AND BULL=?",argss1)
            rsaaa1 = dba1.rawQuery("SELECT * FROM OGRETIMGECICI WHERE BULL = ?",argsss1)

            if (rsaa1.moveToNext()){
                if (rsaaa1.moveToNext()){
                    Toast.makeText(applicationContext,"Giriş Başarılı", Toast.LENGTH_LONG).show()
                    editor.putString("tc",editTextTcOgretim.text.toString().trim())
                    editor.commit()
                    if (editTextTcOgretim.text.toString() == editTextPasswordOgretim.text.toString()){
                        intent = Intent(applicationContext,ogretimsifreyenileme::class.java)
                        intent.putExtra("atc",editTextTcOgretim.text.toString().trim())
                        startActivity(intent)
                    }else{
                        intent = Intent(applicationContext,ogretimgorevlisiekrani::class.java)
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(applicationContext,"Giriş Başarısız",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(applicationContext,"Giriş Başarısız",Toast.LENGTH_LONG).show()
            }
        }

    }
}