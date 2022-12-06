package com.example.astraeaquizapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlin.reflect.typeOf

class MainPage : AppCompatActivity() {

    var englishList = ArrayList<String>()
    var turkishList = ArrayList<String>()
    var map = mapOf<String, String>()
    var chosenWord = ""

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

        // close the cursor
        cursor.close()

        map = englishList.zip(turkishList).toMap() // creates a dict


        val meyveler = listOf("Elma", "Armut", "Muz", "Kivi", "Çilek", "Karpuz", "Kavun", "Ananas", "Kiraz", "Dut")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, englishList)
        val myList = findViewById<ListView>(R.id.words_list)

        myList.adapter = adapter

        myList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val secilenMeyve = parent.getItemAtPosition(position) as String
            chosenWord = secilenMeyve

            CustomDialogClass(this, secilenMeyve, map).show()

            Toast.makeText(this@MainPage, "Seçilen meyve: $secilenMeyve", Toast.LENGTH_SHORT).show()
        }


        val buttonClickForQuiz = findViewById<Button>(R.id.takeQuizButton)
        buttonClickForQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java )
            intent.putExtra("dict", "$map")
            val shuffledEnglishList = englishList.shuffled() as ArrayList<String>

            // if (shuffledEnglishList.isEmpty()) {
            //     shuffledEnglishList = listOf("No words at main page")
            // }
            //intent.putStringArrayListExtra("shuffledEnglishList", shuffledEnglishList)

            // get turkish of the first element of shuffledEnglishList from database
            val cursor = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[0]}'", null)
            val turkishIndex = cursor.getColumnIndex("turkish")
            var turkishOfFirstElement = ""
            while (cursor.moveToNext()) {
                turkishOfFirstElement = cursor.getString(turkishIndex)
            }
            Log.d("turkishOfFirstElement", turkishOfFirstElement)

            // get turkish of the second element of shuffledEnglishList from database
            val cursor2 = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[1]}'", null)
            val turkishIndex2 = cursor2.getColumnIndex("turkish")
            var turkishOfSecondElement = ""
            while (cursor2.moveToNext()) {
                turkishOfSecondElement = cursor2.getString(turkishIndex2)
            }

            // get turkish of the third element of shuffledEnglishList from database
            val cursor3 = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[2]}'", null)
            val turkishIndex3 = cursor3.getColumnIndex("turkish")
            var turkishOfThirdElement = ""
            while (cursor3.moveToNext()) {
                turkishOfThirdElement = cursor3.getString(turkishIndex3)
            }

            // get turkish of the fourth element of shuffledEnglishList from database
            val cursor4 = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[3]}'", null)
            val turkishIndex4 = cursor4.getColumnIndex("turkish")
            var turkishOfFourthElement = ""
            while (cursor4.moveToNext()) {
                turkishOfFourthElement = cursor4.getString(turkishIndex4)
            }

            intent.putExtra("question", shuffledEnglishList[0])
            intent.putExtra("answer1", turkishOfFourthElement)
            intent.putExtra("answer2", turkishOfSecondElement)
            intent.putExtra("answer3", turkishOfThirdElement)
            intent.putExtra("correctanswer", turkishOfFirstElement)
            startActivity(intent)
        }

    }


}