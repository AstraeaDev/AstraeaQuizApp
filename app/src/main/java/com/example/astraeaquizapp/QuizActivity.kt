package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // create an intent
        val intent = Intent(this, QuizActivity::class.java)
        // val map: Map<String, String> = intent.getSerializableExtra("map") as Map<String, String>
        val question = getIntent().getStringExtra("question")
        val answerString1 = getIntent().getStringExtra("answer1")
        val answerString2 = getIntent().getStringExtra("answer2")
        val answerString3 = getIntent().getStringExtra("answer3")
        val correctAnswer = getIntent().getStringExtra("correctanswer")

        var answers = listOf(answerString1, answerString2, answerString3, correctAnswer)
        answers = answers.shuffled()



        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val answer1 = findViewById<RadioButton>(R.id.radioButton)
        val answer2 = findViewById<RadioButton>(R.id.radioButton2)
        val answer3 = findViewById<RadioButton>(R.id.radioButton3)
        val answer4 = findViewById<RadioButton>(R.id.radioButton4)

        tvQuestion.setText("What is the meaning of $question?")
        answer1.setText(answers.get(0))
        answer2.setText(answers.get(1))
        answer3.setText(answers.get(2))
        answer4.setText(answers.get(3))

    }
}