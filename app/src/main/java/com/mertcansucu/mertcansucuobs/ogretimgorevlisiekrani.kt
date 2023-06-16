package com.mertcansucu.mertcansucuobs
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_ogrenciekrani.*
import kotlinx.android.synthetic.main.activity_ogretimgorevlisiekrani.*
import kotlinx.android.synthetic.main.activity_yoneticiekrani.*
import kotlinx.android.synthetic.main.navigation_baslik_ogretim.view.*
class ogretimgorevlisiekrani : AppCompatActivity() {
    lateinit var dbogrtm : SQLiteDatabase
    lateinit var rsogrtm: Cursor
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ogretimgorevlisiekrani)

        sharedPreferences=applicationContext.getSharedPreferences("Info", AppCompatActivity.MODE_PRIVATE)
        editor=sharedPreferences.edit()

        val tc=sharedPreferences.getString("tc","")
        var arg2 = listOf<String>(tc.toString()).toTypedArray()

        val ogretimGeciciHelper = OgretimGeciciHelper(applicationContext)
        dbogrtm = ogretimGeciciHelper.readableDatabase
        rsogrtm = dbogrtm.rawQuery("SELECT OGRTMADSYD FROM OGRETIMGECICI WHERE TCUYE = ?",arg2)
        var text1=""
        while (rsogrtm.moveToNext()){
            text1=rsogrtm.getString(0)
        }
        val navHostFragment2 = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_ogretim) as NavHostFragment
        NavigationUI.setupWithNavController(navigationViewOgretim,navHostFragment2.navController)
        toolbar3.title = "Öğretim Üyesi Sistemi"

        val toogle2 = ActionBarDrawerToggle(this,drawerogretim,toolbar3,0,0)

        drawerogretim.addDrawerListener(toogle2)
        toogle2.syncState()

        val baslikOgretim = navigationViewOgretim.inflateHeaderView(R.layout.navigation_baslik_ogretim)
        baslikOgretim.textViewBaslikOgretim.text = text1
    }
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}