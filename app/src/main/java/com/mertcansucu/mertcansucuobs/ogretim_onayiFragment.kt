package com.mertcansucu.mertcansucuobs

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.fragment_ogretim_onayi.*
class ogretim_onayiFragment : Fragment() {
    lateinit var dbogretim : SQLiteDatabase
    lateinit var rsogretim : Cursor
    lateinit var adapterogretim : SimpleCursorAdapter
    lateinit var controlDegerOgretim : String
    private lateinit var liste1 : ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_ogretim_onayi, container, false)

        liste1 = view.findViewById(R.id.liste1)

        val ogretimGeciciHelper = OgretimGeciciHelper(requireContext())

        dbogretim = ogretimGeciciHelper.readableDatabase
        rsogretim = dbogretim.rawQuery("SELECT * FROM OGRETIMGECICI WHERE BULL=0",null)

        adapterogretim = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsogretim,
            arrayOf("OGRTMADSYD","OGRTMUNVAN"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )

        liste1.adapter = adapterogretim

        registerForContextMenu(liste1)


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
        controlDegerOgretim = "1"
        if (item.itemId ==11){
            var cva = ContentValues()
            cva.put("BULL",controlDegerOgretim)
            dbogretim.update("OGRETIMGECICI",cva,"_id = ?", arrayOf(rsogretim.getString(0)))
            rsogretim.requery()
            adapterogretim.notifyDataSetChanged()
        }
        if (item.itemId==2){
            dbogretim.delete("OGRETIMGECICI","_id = ?", arrayOf(rsogretim.getString(0)))
            rsogretim.requery()
            adapterogretim.notifyDataSetChanged()
        }
        return super.onContextItemSelected(item)
    }


}