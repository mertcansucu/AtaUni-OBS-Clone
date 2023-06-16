package com.mertcansucu.mertcansucuobs

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
class yemek_listesiFragment : Fragment() {
    lateinit var dbyemeklistesi : SQLiteDatabase
    lateinit var rsyemeklistesi: Cursor
    lateinit var adapteryemeklistesi : SimpleCursorAdapter
    private lateinit var listeYemek : ListView
    private lateinit var editTextYemekAdi : EditText
    private lateinit var editTextYemekTarih : EditText
    private lateinit var buttonYemekEkle : Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_yemek_listesi, container, false)

        editTextYemekAdi = view.findViewById(R.id.editTextYemekAdi)
        editTextYemekTarih = view.findViewById(R.id.editTextYemekTarih)
        buttonYemekEkle = view.findViewById(R.id.buttonYemekEkle)

        listeYemek = view.findViewById(R.id.listeYemek)

        val yemekListesiHelper = YemekListesiHelper(requireContext())

        dbyemeklistesi = yemekListesiHelper.readableDatabase
        rsyemeklistesi = dbyemeklistesi.rawQuery("SELECT * FROM YEMEKLISTETABLE",null)

        adapteryemeklistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsyemeklistesi,
            arrayOf("YEMEK","TARIH"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )

        listeYemek.adapter = adapteryemeklistesi

        buttonYemekEkle.setOnClickListener {
            var cvyemek = ContentValues()
            cvyemek.put("YEMEK",editTextYemekAdi.text.toString())
            cvyemek.put("TARIH",editTextYemekTarih.text.toString())
            dbyemeklistesi.insert("YEMEKLISTETABLE",null,cvyemek)
            rsyemeklistesi.requery()
            adapteryemeklistesi.notifyDataSetChanged()

            editTextYemekAdi.setText("")
            editTextYemekTarih.setText("")

            Toast.makeText(requireContext(),"Yemek Listesine Eklendi", Toast.LENGTH_LONG).show()
        }

        registerForContextMenu(listeYemek)

        return view
    }
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(101,11,1,"Kullanıcıyı Sil")
        menu.setHeaderTitle("Kullanıcı")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId==1){
            dbyemeklistesi.delete("YEMEKLISTETABLE","_id = ?", arrayOf(rsyemeklistesi.getString(0)))
            rsyemeklistesi.requery()
            adapteryemeklistesi.notifyDataSetChanged()
        }
        return super.onContextItemSelected(item)
    }


}