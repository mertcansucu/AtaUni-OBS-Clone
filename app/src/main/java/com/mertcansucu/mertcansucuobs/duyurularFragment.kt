package com.mertcansucu.mertcansucuobs
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
class duyurularFragment : Fragment() {
    lateinit var dbduyurulistesi : SQLiteDatabase
    lateinit var rsduyurulistesi: Cursor
    lateinit var adapterduyurulistesi : SimpleCursorAdapter
    private lateinit var listeDuyuru : ListView
    private lateinit var editTextDuyuruBaslik : EditText
    private lateinit var editTextDuyuruKonu : EditText
    private lateinit var buttonDuyuruEkle : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_duyurular, container, false)
        editTextDuyuruBaslik = view.findViewById(R.id.editTextDuyuruBaslik)
        editTextDuyuruKonu = view.findViewById(R.id.editTextDuyuruKonu)
        buttonDuyuruEkle = view.findViewById(R.id.buttonDuyuruEkle)
        listeDuyuru = view.findViewById(R.id.listeDuyuru)
        val duyurularHelper = DuyurularHelper(requireContext())
        dbduyurulistesi = duyurularHelper.readableDatabase
        rsduyurulistesi = dbduyurulistesi.rawQuery("SELECT * FROM DUYURULISTETABLE",null)
        adapterduyurulistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsduyurulistesi,
            arrayOf("DUYURUBASLIK","DUYURUKONU"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )
        listeDuyuru.adapter = adapterduyurulistesi
        buttonDuyuruEkle.setOnClickListener {
            var cvduyuru = ContentValues()
            cvduyuru.put("DUYURUBASLIK",editTextDuyuruBaslik.text.toString())
            cvduyuru.put("DUYURUKONU",editTextDuyuruKonu.text.toString())
            dbduyurulistesi.insert("DUYURULISTETABLE",null,cvduyuru)
            rsduyurulistesi.requery()
            adapterduyurulistesi.notifyDataSetChanged()
            editTextDuyuruBaslik.setText("")
            editTextDuyuruKonu.setText("")
            Toast.makeText(requireContext(),"Duyuru Eklendi", Toast.LENGTH_LONG).show()
        }
        return view
    }

}