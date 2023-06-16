package com.mertcansucu.mertcansucuobs

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_yoneticiekrani.*

class MainActivity : AppCompatActivity() {
    lateinit var dba : SQLiteDatabase
    lateinit var rsaa : Cursor
    lateinit var rsaaa: Cursor
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences=this.getSharedPreferences("StudenInfo", MODE_PRIVATE)
        editor=sharedPreferences.edit()
        val buttonGirisOgrenci = findViewById<Button>(R.id.buttonGirisOgrenci)

        val buttonYonetici = findViewById<Button>(R.id.buttonYonetici)

        val buttonOgretim = findViewById<Button>(R.id.buttonOgretim)

        val buttonKayit = findViewById<Button>(R.id.buttonKayit)

        val buttonOgrenciEkrani = findViewById<Button>(R.id.buttonOgrenciEkrani)

        val editTextPasswordOgrenci = findViewById<EditText>(R.id.editTextPasswordOgrenci)

        val editTextTcOgrenci = findViewById<EditText>(R.id.editTextTcOgrenci)

        var ogrenciGeciciHelper = OgrenciGeciciHelper(applicationContext)
        dba = ogrenciGeciciHelper.readableDatabase
        buttonOgrenciEkrani.setOnClickListener {
            intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
        buttonYonetici.setOnClickListener {
            intent = Intent(applicationContext,yonetici_giris::class.java)
            startActivity(intent)
        }
        buttonOgretim.setOnClickListener {
            intent = Intent(applicationContext,ogretim_giris::class.java)
            startActivity(intent)
        }
        buttonKayit.setOnClickListener {
            intent = Intent(applicationContext,kayitol::class.java)
            startActivity(intent)
        }
        buttonGirisOgrenci.setOnClickListener {

            var yeniControl : String
            yeniControl = "1"
            var argsss = listOf<String>(yeniControl).toTypedArray()
            var argss = listOf<String>(editTextTcOgrenci.text.toString(),editTextPasswordOgrenci.text.toString(),"1").toTypedArray()
            rsaa = dba.rawQuery("SELECT * FROM OGRENCIGECICI WHERE TCUYE = ? AND OGRNCSIFRE = ? AND BUL=?",argss)
            rsaaa = dba.rawQuery("SELECT * FROM OGRENCIGECICI WHERE BUL = ?",argsss)

            if(rsaa.moveToNext()){
                if (rsaaa.moveToNext()){
                    Toast.makeText(applicationContext,"Giriş Başarılı", Toast.LENGTH_LONG).show()
                    editor.putString("tc",editTextTcOgrenci.text.toString().trim())
                    editor.commit()
                    if (editTextTcOgrenci.text.toString() ==  editTextPasswordOgrenci.text.toString()){

                        intent = Intent(applicationContext,ogrencisifreyenileme::class.java)
                        intent.putExtra("tc",editTextTcOgrenci.text.toString().trim())
                        startActivity(intent)
                    } else{
                        intent = Intent(applicationContext,ogrenciekrani::class.java)
                        startActivity(intent)
                    }
                }else{
                Toast.makeText(applicationContext,"Giriş Başarısız",Toast.LENGTH_LONG).show()
            }
        } else{
                Toast.makeText(applicationContext,"Giriş Başarısız",Toast.LENGTH_LONG).show()
            }
    }
}
}