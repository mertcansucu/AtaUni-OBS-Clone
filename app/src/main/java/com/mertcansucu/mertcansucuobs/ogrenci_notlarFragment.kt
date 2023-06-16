package com.mertcansucu.mertcansucuobs

import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity

class ogrenci_notlarFragment : Fragment() {
    lateinit var dbdersnotlistesi : SQLiteDatabase
    lateinit var rsdersnotlistesi: Cursor
    lateinit var adapterdersnotlistesi : SimpleCursorAdapter
    private lateinit var listeOgrenciNot : ListView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ogrenci_notlar, container, false)

        sharedPreferences=requireContext().getSharedPreferences("StudenInfo", AppCompatActivity.MODE_PRIVATE)
        editor=sharedPreferences.edit()

        val tc=sharedPreferences.getString("tc","")

        var arg = listOf<String>(tc.toString()).toTypedArray()

        listeOgrenciNot = view.findViewById(R.id.listeOgrenciNot)

        val dersNotttHelperr = DersNotttHelperr(requireContext())

        dbdersnotlistesi = dersNotttHelperr.readableDatabase
        rsdersnotlistesi = dbdersnotlistesi.rawQuery("SELECT * FROM DERSLISTENOTTABLE WHERE TCUYE = ?",arg)

        adapterdersnotlistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsdersnotlistesi,
            arrayOf("DERSADI","DERSHARFNOT"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )

        listeOgrenciNot.adapter = adapterdersnotlistesi
        return view
    }
}