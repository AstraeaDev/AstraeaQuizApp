package com.example.astraeaquizapp


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainPage : AppCompatActivity() {

    var englishList = ArrayList<String>()
    var turkishList = ArrayList<String>()
    var map = mapOf<String, String>()
    var chosenWord = ""

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIF_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        // open an sqlite database
        val db = openOrCreateDatabase("quizbook", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS book (id INTEGER PRIMARY KEY, genreID INTEGER, english VARCHAR, turkish VARCHAR)")

        // check if the database is empty
        var cursor = db.rawQuery("SELECT * FROM book", null)
        if (cursor.count == 0) {
            // fill the database with the number words and define the genreID as 1
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'one', 'bir')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'two', 'iki')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'three', 'üç')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'four', 'dört')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'five', 'beş')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'six', 'altı')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'seven', 'yedi')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'eight', 'sekiz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'nine', 'dokuz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (1, 'ten', 'on')")


            // fill the database with the color words and define the genreID as 2
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'red', 'kırmızı')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'blue', 'mavi')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'green', 'yeşil')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'yellow', 'sarı')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'orange', 'turuncu')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'purple', 'mor')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'pink', 'pembe')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'black', 'siyah')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'white', 'beyaz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'brown', 'kahverengi')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'grey', 'gri')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'gold', 'altın')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'silver', 'gümüş')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'bronze', 'bronz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'violet', 'menekşe')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (2, 'indigo', 'lacivert')")

            // fill the database with the animal words and define the genreID as 3
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'dog', 'köpek')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'cat', 'kedi')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'bird', 'kuş')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'fish', 'balık')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'horse', 'at')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'cow', 'inek')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'sheep', 'koyun')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'pig', 'domuz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'chicken', 'tavuk')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'duck', 'ördek')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'goat', 'keçi')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'elephant', 'fil')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'lion', 'aslan')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (3, 'tiger', 'kaplan')")

            // fill the database with the fruit words and define the genreID as 4
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'apple', 'elma')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'banana', 'muz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'orange', 'portakal')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'grape', 'üzüm')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'strawberry', 'çilek')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'watermelon', 'karpuz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'pineapple', 'ananas')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'pear', 'armut')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'peach', 'şeftali')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'cherry', 'kiraz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (4, 'lemon', 'limon')")


            // fill the database with the vegetable words and define the genreID as 5
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (5, 'tomato', 'domates')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (5, 'potato', 'patates')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (5, 'onion', 'soğan')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (5, 'carrot', 'havuç')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (5, 'cucumber', 'salatalık')")

            // fill the database with the body words and define the genreID as 6
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (6, 'head', 'kafa')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (6, 'eye', 'göz')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (6, 'ear', 'kulak')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (6, 'nose', 'burun')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (6, 'mouth', 'ağız')")

            // fill the database with the family words and define the genreID as 7
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (7, 'father', 'baba')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (7, 'mother', 'anne')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (7, 'son', 'oğul')")
            db.execSQL("INSERT INTO book (genreID, english, turkish) VALUES (7, 'daughter', 'kız')")

        }


        // get data from database
        cursor = db.rawQuery("SELECT * FROM book", null)
        val englishIndex = cursor.getColumnIndex("english")
        val turkishIndex = cursor.getColumnIndex("turkish")

        while (cursor.moveToNext()) {
            englishList.add(cursor.getString(englishIndex))
            turkishList.add(cursor.getString(turkishIndex))
        }

        // close the cursor
        cursor.close()

        map = englishList.zip(turkishList).toMap() // creates a dict



        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, englishList)
        val myList = findViewById<ListView>(R.id.words_list)

        myList.adapter = adapter

        myList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val secilenMeyve = parent.getItemAtPosition(position) as String
            chosenWord = secilenMeyve

            CustomDialogClass(this, secilenMeyve, map).show()

        }


        val buttonClickForQuiz = findViewById<Button>(R.id.takeQuizButton)
        buttonClickForQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java )
            intent.putExtra("dict", "$map")
            val shuffledEnglishList = englishList.shuffled() as ArrayList<String>

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

            createNotifChannel()
            val intentNotification=Intent(this,MainPage::class.java)
            val pendingIntent = TaskStackBuilder.create(this).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            }

            val notif = NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Brave Step!")
                .setContentText("Keep solving quizzes and get better!")
                .setSmallIcon(R.drawable.astraea_logo)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .build()

            val notifManger = NotificationManagerCompat.from(this)
            notifManger.notify(NOTIF_ID,notif)

            intent.putExtra("question", shuffledEnglishList[0])
            intent.putExtra("answer1", turkishOfFourthElement)
            intent.putExtra("answer2", turkishOfSecondElement)
            intent.putExtra("answer3", turkishOfThirdElement)
            intent.putExtra("correctanswer", turkishOfFirstElement)
            startActivity(intent)
        }

    }
    private fun createNotifChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}