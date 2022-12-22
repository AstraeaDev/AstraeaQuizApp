package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class QuizActivity : AppCompatActivity() {
    var englishList = ArrayList<String>()
    var turkishList = ArrayList<String>()
    var turkishOfFourthElement = ""
    var quizScore = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        //cagatay
        // kelime databaseini quizactivityde de açarsak burda sorgularını yapabiliriz. Sonuçta oluşturulmuş bir database olucak.
        val db = openOrCreateDatabase("quizbook", MODE_PRIVATE, null)


        //get data from database
        val cursor = db.rawQuery("SELECT * FROM book", null)
        val englishIndex = cursor.getColumnIndex("english")
        val turkishIndex = cursor.getColumnIndex("turkish")



        while (cursor.moveToNext()) {
            englishList.add(cursor.getString(englishIndex))
            turkishList.add(cursor.getString(turkishIndex))
        }

        val shuffledEnglishList = englishList.shuffled() as ArrayList<String>

        //cagatay-

        // val map: Map<String, String> = intent.getSerializableExtra("map") as Map<String, String>
        val question = getIntent().getStringExtra("question")
        val answerString1 = getIntent().getStringExtra("answer1")
        val answerString2 = getIntent().getStringExtra("answer2")
        val answerString3 = getIntent().getStringExtra("answer3")
        val correctAnswer = getIntent().getStringExtra("correctanswer")


        var answersDeneme = listOf(answerString1, answerString2, answerString3, correctAnswer,question)
        answersDeneme = answersDeneme.shuffled()


        //cagatay
        /*
        // get turkish of the fourth element of shuffledEnglishList from database
        val cursor4 = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[0]}'", null)
        val turkishIndex4 = cursor4.getColumnIndex("turkish")
        var turkishOfFourthElement = ""
        while (cursor4.moveToNext()) {
            turkishOfFourthElement = cursor4.getString(turkishIndex4)
        }


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





        // get turkish of the first element of shuffledEnglishList from database
        val cursorA = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[3]}'", null)
        val turkishIndexA = cursorA.getColumnIndex("turkish")
        var turkishOfFirstElement = ""
        while (cursorA.moveToNext()) {
            turkishOfFirstElement = cursorA.getString(turkishIndexA)
        }




         */

        //cagatay-



        /*
        var answers = listOf(turkishOfFirstElement, turkishOfSecondElement, turkishOfThirdElement, turkishOfFourthElement)
        answers = answers.shuffled()

         */


        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val answer1 = findViewById<RadioButton>(R.id.radioButton)
        val answer2 = findViewById<RadioButton>(R.id.radioButton2)
        val answer3 = findViewById<RadioButton>(R.id.radioButton3)
        val answer4 = findViewById<RadioButton>(R.id.radioButton4)
        val choices = findViewById<RadioGroup>(R.id.choices)
        val quizEnd = findViewById<TextView>(R.id.quizEnd)
        val finishButton = findViewById<Button>(R.id.finishButton)



        tvQuestion.setText("Your questions will be appeared in here")
        answer1.setText("Answer 1")
        answer2.setText("Answer 2")
        answer3.setText("Answer 3")
        answer4.setText("Answer 4")




        /*
        tvQuestion.setText("What is the meaning of ${shuffledEnglishList.get(i)}")
        answer1.setText(answers.get(0))
        answer2.setText(answers.get(1))
        answer3.setText(answers.get(2))
        answer4.setText(answers.get(3))

         */

        val button = findViewById<Button>(R.id.button)
        var i = 0
        //var quizScore = 0
        quizEnd.visibility = View.GONE
        finishButton.visibility =View.GONE
        button.setOnClickListener(){


            //cagatay
            // get turkish of the fourth element of shuffledEnglishList from database
            val cursor4 = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[i]}'", null)
            val turkishIndex4 = cursor4.getColumnIndex("turkish")
            //var turkishOfFourthElement = ""
            while (cursor4.moveToNext()) {
                turkishOfFourthElement = cursor4.getString(turkishIndex4)
            }


            // get turkish of the second element of shuffledEnglishList from database
            val cursor2 = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[i+1]}'", null)
            val turkishIndex2 = cursor2.getColumnIndex("turkish")
            var turkishOfSecondElement = ""
            while (cursor2.moveToNext()) {
                turkishOfSecondElement = cursor2.getString(turkishIndex2)
            }




            // get turkish of the third element of shuffledEnglishList from database
            val cursor3 = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[i+2]}'", null)
            val turkishIndex3 = cursor3.getColumnIndex("turkish")
            var turkishOfThirdElement = ""
            while (cursor3.moveToNext()) {
                turkishOfThirdElement = cursor3.getString(turkishIndex3)
            }





            // get turkish of the first element of shuffledEnglishList from database
            val cursorA = db.rawQuery("SELECT * FROM book WHERE english = '${shuffledEnglishList[i+3]}'", null)
            val turkishIndexA = cursorA.getColumnIndex("turkish")
            var turkishOfFirstElement = ""
            while (cursorA.moveToNext()) {
                turkishOfFirstElement = cursorA.getString(turkishIndexA)
            }

            var answers = listOf(turkishOfFirstElement, turkishOfSecondElement, turkishOfThirdElement, turkishOfFourthElement)
            answers = answers.shuffled()

            tvQuestion.setText("What is the meaning of ${shuffledEnglishList.get(i)}")
            answer1.setText(answers.get(0))
            answer2.setText(answers.get(1))
            answer3.setText(answers.get(2))
            answer4.setText(answers.get(3))

            i++


            choices.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener { _, checkedId ->
                    val radio: RadioButton = findViewById(checkedId)
                    if (radio.text == turkishOfFourthElement) {
                        /*
                         val intent = Intent(this, QuizActivity::class.java)
                         startActivity(intent)
                         Toast.makeText(applicationContext,"Correct Answer!:"+
                                 " ${radio.text}",
                             Toast.LENGTH_SHORT).show()

                         */

                        quizScore ++



                    }
                }

            )


            if(i==10){
                button.visibility = View.GONE
                tvQuestion.visibility = View.GONE
                answer1.visibility = View.GONE
                answer2.visibility = View.GONE
                answer3.visibility = View.GONE
                answer4.visibility = View.GONE
                quizEnd.visibility = View.VISIBLE
                quizEnd.setText("Your Score : ${quizScore}")
                finishButton.visibility = View.VISIBLE



            }



        }


        finishButton.setOnClickListener(){
            val intent = Intent(this,MainPage::class.java)
            startActivity(intent)
        }



    }
}