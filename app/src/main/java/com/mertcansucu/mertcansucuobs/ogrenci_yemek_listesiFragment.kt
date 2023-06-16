package com.mertcansucu.mertcansucuobs

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import java.time.LocalDateTime
import kotlin.properties.Delegates

class ogrenci_yemek_listesiFragment : Fragment() {
    lateinit var dbyemeklistesi : SQLiteDatabase
    lateinit var rsyemeklistesi: Cursor
    lateinit var adapteryemeklistesi : SimpleCursorAdapter
    private lateinit var listeyemekOgrenci : ListView
    private lateinit var tarih : LocalDateTime
    private lateinit var arguman : Array<String>
    var gun by Delegates.notNull<Int>()
    var ay by Delegates.notNull<Int>()
    var yil by Delegates.notNull<Int>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ogrenci_yemek_listesi, container, false)
        listeyemekOgrenci = view.findViewById(R.id.listeyemekOgrenci)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tarih = LocalDateTime.now()

            gun = tarih.dayOfMonth
            ay = tarih.monthValue
            yil = tarih.year
        }

        var tamTarih = "$gun/$ay/$yil"

        arguman = listOf<String>(tamTarih).toTypedArray()

        val yemekListesiHelper = YemekListesiHelper(requireContext())

        dbyemeklistesi = yemekListesiHelper.readableDatabase
        rsyemeklistesi = dbyemeklistesi.rawQuery("SELECT * FROM YEMEKLISTETABLE WHERE TARIH = ? ",arguman)

        adapteryemeklistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsyemeklistesi,
            arrayOf("YEMEK","TARIH"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )
        listeyemekOgrenci.adapter = adapteryemeklistesi
        return view
    }
}