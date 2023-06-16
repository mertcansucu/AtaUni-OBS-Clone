package com.mertcansucu.mertcansucuobs

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleCursorAdapter

class ogretim_duyurular : Fragment() {
    lateinit var dbduyurulistesi : SQLiteDatabase
    lateinit var rsduyurulistesi: Cursor
    lateinit var adapterduyurulistesi : SimpleCursorAdapter
    private lateinit var listeduyurularOgretim : ListView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ogretim_duyurular, container, false)

        listeduyurularOgretim = view.findViewById(R.id.listeduyurularOgretim)

        val duyurularHelper = DuyurularHelper(requireContext())

        dbduyurulistesi = duyurularHelper.readableDatabase
        rsduyurulistesi = dbduyurulistesi.rawQuery("SELECT * FROM DUYURULISTETABLE",null)

        adapterduyurulistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsduyurulistesi,
            arrayOf("DUYURUBASLIK","DUYURUKONU"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )

        listeduyurularOgretim.adapter = adapterduyurulistesi


        return view
    }

}