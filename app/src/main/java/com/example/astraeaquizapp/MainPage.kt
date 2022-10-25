package com.example.astraeaquizapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val meyveler = listOf("Elma", "Armut", "Muz", "Kivi", "Çilek", "Karpuz", "Kavun", "Ananas", "Kiraz", "Dut")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, meyveler)
        val myList = findViewById<ListView>(R.id.words_list)

        myList.adapter = adapter

        myList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val secilenMeyve = parent.getItemAtPosition(position) as String

            CustomDialogClass(this).show()

            Toast.makeText(this@MainPage, "Seçilen meyve: $secilenMeyve", Toast.LENGTH_SHORT).show()
        }



    }


}