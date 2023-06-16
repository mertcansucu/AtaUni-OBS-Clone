package com.mertcansucu.mertcansucuobs

import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AlertDialog

class OgrenciOnayiFragment : Fragment() {
    lateinit var dbaa : SQLiteDatabase
    lateinit var rsaaaa : Cursor
    lateinit var adapterr : SimpleCursorAdapter
    lateinit var controlDegerr : String
    private lateinit var liste : ListView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_ogrenci_onayi, container, false)
        //edTotal = findViewById(R.id.edTotal)
        liste = view.findViewById(R.id.liste)

        val ogrenciGeciciHelper = OgrenciGeciciHelper(requireContext())
        //getActivity().getApplicationContext()
        dbaa = ogrenciGeciciHelper.readableDatabase
        rsaaaa = dbaa.rawQuery("SELECT * FROM OGRENCIGECICI WHERE BUL=0",null)


        adapterr = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsaaaa,
            arrayOf("OGRNCMADSYD","TCUYE"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )
        liste.adapter = adapterr

        registerForContextMenu(liste)

        return view
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(101,11,1,"Kullanıcıyı Ekle")
        menu.add(202,22,2,"Kullanıcıyı Sil")
        menu.setHeaderTitle("Kullanıcı")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        controlDegerr = "1"
        if (item.itemId == 11){
            var cv = ContentValues()
            cv.put("BUL",controlDegerr)
            dbaa.update("OGRENCIGECICI",cv,"_id = ?", arrayOf(rsaaaa.getString(0)))
            rsaaaa.requery()
            adapterr.notifyDataSetChanged()

        }
        if (item.itemId==22){
            dbaa.delete("OGRENCIGECICI","_id = ?", arrayOf(rsaaaa.getString(0)))
            rsaaaa.requery()
            adapterr.notifyDataSetChanged()
        }
        return super.onContextItemSelected(item)
    }

}