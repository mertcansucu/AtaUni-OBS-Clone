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

class ogrenci_derslistesiFragment : Fragment() {
    lateinit var dbderslistesi : SQLiteDatabase
    lateinit var rsderslistesi: Cursor
    lateinit var adapterderslistesi : SimpleCursorAdapter
    private lateinit var liste5 : ListView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ogrenci_derslistesi, container, false)

        liste5 = view.findViewById(R.id.liste5)

        val derslistesiHelper = DerslistesiHelper(requireContext())

        dbderslistesi = derslistesiHelper.readableDatabase
        rsderslistesi = dbderslistesi.rawQuery("SELECT * FROM DERSLISTETABLE",null)


        adapterderslistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsderslistesi,
            arrayOf("DERSADI","DERSKODU"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )

        liste5.adapter = adapterderslistesi

        return view
    }


}