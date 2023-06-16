package com.mertcansucu.mertcansucuobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_yoneticiekrani.*
import kotlinx.android.synthetic.main.navigation_baslik_yonetici.view.*

class yoneticiekrani : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoneticiekrani)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_ders_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(navigationView, navHostFragment.navController)

        toolbar.title = "Yönetici Sistemi"

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik_yonetici)
        baslik.textViewBaslikYonetici.text = "Yönetici Sistemi"
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}
