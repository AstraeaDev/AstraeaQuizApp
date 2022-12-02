package com.example.astraeaquizapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainPage : AppCompatActivity() {

    var englishList = ArrayList<String>()
    var turkishList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        // open an sqlite database
        val db = openOrCreateDatabase("quizbook", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY, genreID INTEGER, english VARCHAR, turkish VARCHAR)")

        // insert some data
        // db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'White', 'Beyaz')")

        // get data from database
        val cursor = db.rawQuery("SELECT * FROM book", null)
        val englishIndex = cursor.getColumnIndex("english")
        val turkishIndex = cursor.getColumnIndex("turkish")

        while (cursor.moveToNext()) {
            englishList.add(cursor.getString(englishIndex))
            turkishList.add(cursor.getString(turkishIndex))
        }

        val map: Map<String, String> = englishList.zip(turkishList).toMap() // creates a dict


        val meyveler = listOf("Elma", "Armut", "Muz", "Kivi", "Çilek", "Karpuz", "Kavun", "Ananas", "Kiraz", "Dut")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, englishList)
        val myList = findViewById<ListView>(R.id.words_list)

        myList.adapter = adapter

        myList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val secilenMeyve = parent.getItemAtPosition(position) as String

            CustomDialogClass(this, secilenMeyve, map).show()

            Toast.makeText(this@MainPage, "Seçilen meyve: $secilenMeyve", Toast.LENGTH_SHORT).show()
        }


        val buttonClickForQuiz = findViewById<Button>(R.id.takeQuizButton)
        buttonClickForQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java )
            startActivity(intent)
        }

    }


}