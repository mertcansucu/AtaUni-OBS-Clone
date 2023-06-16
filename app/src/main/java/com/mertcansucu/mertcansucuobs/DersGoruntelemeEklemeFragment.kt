package com.mertcansucu.mertcansucuobs

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
class DersGoruntelemeEklemeFragment : Fragment() {
    lateinit var dbderslistesi : SQLiteDatabase
    lateinit var rsderslistesi: Cursor
    lateinit var adapterderslistesi : SimpleCursorAdapter
    private lateinit var liste3 : ListView
    private lateinit var editTextDersAdi : EditText
    private lateinit var editTextDersKodu : EditText
    private lateinit var buttonDersEkle : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ders_gorunteleme_ekleme, container, false)

        editTextDersAdi = view.findViewById(R.id.editTextDersAdi)
        editTextDersKodu = view.findViewById(R.id.editTextDersKodu)
        buttonDersEkle = view.findViewById(R.id.buttonDersEkle)

        liste3 = view.findViewById(R.id.liste3)

        val derslistesiHelper = DerslistesiHelper(requireContext())

        dbderslistesi = derslistesiHelper.readableDatabase
        rsderslistesi = dbderslistesi.rawQuery("SELECT * FROM DERSLISTETABLE",null)


        adapterderslistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsderslistesi,
            arrayOf("DERSADI","DERSKODU"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )

        liste3.adapter = adapterderslistesi



        buttonDersEkle.setOnClickListener {
            var cvders = ContentValues()
            cvders.put("DERSADI",editTextDersAdi.text.toString())
            cvders.put("DERSKODU",editTextDersKodu.text.toString())
            dbderslistesi.insert("DERSLISTETABLE",null,cvders)
            rsderslistesi.requery()
            adapterderslistesi.notifyDataSetChanged()

            editTextDersAdi.setText("")
            editTextDersKodu.setText("")

            Toast.makeText(requireContext(),"Ders Eklendi", Toast.LENGTH_LONG).show()
        }
        registerForContextMenu(liste3)

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
            dbderslistesi.delete("DERSLISTETABLE","_id = ?", arrayOf(rsderslistesi.getString(0)))
            rsderslistesi.requery()
            adapterderslistesi.notifyDataSetChanged()
        }
        return super.onContextItemSelected(item)
    }



}