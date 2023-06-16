package com.mertcansucu.mertcansucuobs
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
class ogretim_ogrencinotlar : Fragment() {
    lateinit var dbdersnotlistesi : SQLiteDatabase
    lateinit var rsdersnotlistesi: Cursor
    lateinit var adapterdersnotlistesi : SimpleCursorAdapter
    private lateinit var liste6 : ListView
    private lateinit var editTextTcYeni : EditText
    private lateinit var editTextDersAdiYeni : EditText
    private lateinit var editTextDersVize : EditText
    private lateinit var editTextFinal : EditText
    private lateinit var buttonDersNotEkle : Button
    private lateinit var buttonDersTemizle : Button
    private lateinit var buttonDersGuncelle : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_ogretim_ogrencinotlar, container, false)
        editTextTcYeni = view.findViewById(R.id.editTextTcYeni)
        editTextDersAdiYeni = view.findViewById(R.id.editTextDersAdiYeni)
        editTextDersVize = view.findViewById(R.id.editTextDersVize)
        editTextFinal = view.findViewById(R.id.editTextFinal)
        buttonDersNotEkle = view.findViewById(R.id.buttonDersNotEkle)
        buttonDersTemizle = view.findViewById(R.id.buttonDersTemizle)
        buttonDersGuncelle = view.findViewById(R.id.buttonDersGuncelle)


        liste6 = view.findViewById(R.id.liste6)

        val dersNotttHelperr = DersNotttHelperr(requireContext())

        dbdersnotlistesi = dersNotttHelperr.readableDatabase
        rsdersnotlistesi = dbdersnotlistesi.rawQuery("SELECT * FROM DERSLISTENOTTABLE",null)

        adapterdersnotlistesi = SimpleCursorAdapter(requireContext(),android.R.layout.simple_expandable_list_item_2,rsdersnotlistesi,
            arrayOf("TCUYE","DERSHARFNOT"),
            intArrayOf(android.R.id.text1 ,android.R.id.text2),0
        )
        liste6.adapter = adapterdersnotlistesi

        buttonDersTemizle.setOnClickListener {
            editTextTcYeni.setText("")
            editTextDersAdiYeni.setText("")
            editTextDersVize.setText("")
            editTextFinal.setText("")
        }

        buttonDersGuncelle.setOnClickListener {
            var text1 : String
            var text12 : String

            text1 = editTextDersVize.text.toString()
            text12 = editTextFinal.text.toString()

            var number11 : Int = text1.toInt()
            var number12 : Int = text12.toInt()

            var sonuc1 : Int
            sonuc1 = (number11 + number12)/2

            var harfnot : String

            if (sonuc1>=90) {
                harfnot = "AA"
            }
            else if (sonuc1<90 && sonuc1>=84) {

                harfnot = "BA"
            }
            else if (sonuc1<84 && sonuc1>=76) {

                harfnot = "BB"
            }
            else if (sonuc1<76 && sonuc1>=70) {

                harfnot = "CB"
            }
            else if (sonuc1<70 && sonuc1>=64) {

                harfnot = "CC"
            }
            else if (sonuc1<58&& sonuc1>=50) {

                harfnot = "CD"
            }
            else if (sonuc1<50 && sonuc1>=45) {

                harfnot = "DD"
            }
            else{
                harfnot = "FF"
            }

            var cvdersnotGuncelle = ContentValues()
            cvdersnotGuncelle.put("TCUYE",editTextTcYeni.text.toString())
            cvdersnotGuncelle.put("DERSADI",editTextDersAdiYeni.text.toString())
            cvdersnotGuncelle.put("DERSHARFNOT",harfnot)
            dbdersnotlistesi.update("DERSLISTENOTTABLE",cvdersnotGuncelle,"_id = ?", arrayOf(rsdersnotlistesi.getString(0)))
            rsdersnotlistesi.requery()
            adapterdersnotlistesi.notifyDataSetChanged()

            editTextTcYeni.setText("")
            editTextDersAdiYeni.setText("")
            editTextDersVize.setText("")
            editTextFinal.setText("")

        }
        buttonDersNotEkle.setOnClickListener {
            var text : String
            var text2 : String
            text = editTextDersVize.text.toString()
            text2 = editTextFinal.text.toString()
            var number1 : Int = text.toInt()
            var number2 : Int = text2.toInt()

            var sonuc : Int
            sonuc = (number1 + number2)/2

            var harf : String

            if (sonuc>=90) {
                harf = "AA"
            }
            else if (sonuc<90 && sonuc>=84) {

                harf = "BA"
            }
            else if (sonuc<84 && sonuc>=76) {

                harf = "BB"
            }
            else if (sonuc<76 && sonuc>=70) {

                harf = "CB"
            }
            else if (sonuc<70 && sonuc>=64) {

                harf = "CC"
            }
            else if (sonuc<58&& sonuc>=50) {

                harf = "CD"
            }
            else if (sonuc<50 && sonuc>=45) {

                harf = "DD"
            }
            else{
                harf = "FF"
            }


            var cvdersnot = ContentValues()
            cvdersnot.put("TCUYE",editTextTcYeni.text.toString())
            cvdersnot.put("DERSADI",editTextDersAdiYeni.text.toString())
            cvdersnot.put("DERSHARFNOT",harf)
            dbdersnotlistesi.insert("DERSLISTENOTTABLE",null,cvdersnot)
            rsdersnotlistesi.requery()
            adapterdersnotlistesi.notifyDataSetChanged()

            editTextTcYeni.setText("")
            editTextDersAdiYeni.setText("")
            editTextDersVize.setText("")
            editTextFinal.setText("")

        }

       registerForContextMenu(liste6)


        return view


    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(101,11,1,"Kullanıcıyı Seç")
        menu.add(303,33,3,"Kullanıcıyı Sil")
        menu.setHeaderTitle("Kullanıcı")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId==11){
            editTextTcYeni.setText(rsdersnotlistesi.getString(1))
            editTextDersAdiYeni.setText(rsdersnotlistesi.getString(2))
        }
        if (item.itemId==33){
            dbdersnotlistesi.delete("DERSLISTENOTTABLE","_id = ?", arrayOf(rsdersnotlistesi.getString(0)))
            rsdersnotlistesi.requery()
            adapterdersnotlistesi.notifyDataSetChanged()

            editTextTcYeni.setText("")
            editTextDersAdiYeni.setText("")
            editTextDersVize.setText("")
            editTextFinal.setText("")
        }
        return super.onContextItemSelected(item)
    }


}