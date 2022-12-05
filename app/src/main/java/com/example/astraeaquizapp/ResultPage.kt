package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ResultPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        val buttonClickForMainPage = findViewById<Button>(R.id.resultButton)
        buttonClickForMainPage.setOnClickListener {
            val intent = Intent(this, MainPage::class.java )
            startActivity(intent)
        }

    }
}