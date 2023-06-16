package com.mertcansucu.mertcansucuobs
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_ogrenciekrani.*
import kotlinx.android.synthetic.main.activity_yoneticiekrani.*
import kotlinx.android.synthetic.main.navigation_baslik_ogrenci.view.*
class ogrenciekrani : AppCompatActivity() {
    lateinit var dbogr : SQLiteDatabase
    lateinit var rsogr: Cursor
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ogrenciekrani)
        sharedPreferences=applicationContext.getSharedPreferences("StudenInfo", AppCompatActivity.MODE_PRIVATE)
        editor=sharedPreferences.edit()
        val tc=sharedPreferences.getString("tc","")
        var arg1 = listOf<String>(tc.toString()).toTypedArray()

        val ogrenciGeciciHelper = OgrenciGeciciHelper(applicationContext)
        dbogr = ogrenciGeciciHelper.readableDatabase
        rsogr = dbogr.rawQuery("SELECT OGRNCMADSYD FROM OGRENCIGECICI WHERE TCUYE = ?",arg1)
       var text=""
        while (rsogr.moveToNext()){
            text=rsogr.getString(0)
        }
        val navHostFragment1 =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_ogrenci) as NavHostFragment

        NavigationUI.setupWithNavController(navigationViewOgrenci, navHostFragment1.navController)

        toolbar2.title = "Öğrenci Sistemi"

        val toggle1 = ActionBarDrawerToggle(this, drawerogrenci, toolbar2, 0, 0)

        drawerogrenci.addDrawerListener(toggle1)
        toggle1.syncState()

        val baslikOgrenci = navigationViewOgrenci.inflateHeaderView(R.layout.navigation_baslik_ogrenci)
        baslikOgrenci.textViewBaslikOgrenci.text = text
    }
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
