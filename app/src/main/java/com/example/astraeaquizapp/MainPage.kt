package com.example.astraeaquizapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

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


        val buttonClickForQuiz = findViewById<Button>(R.id.takeQuizButton)
        buttonClickForQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java )
            startActivity(intent)
        }

    }


}