package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.login_button)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }

        val buttonClick2 = findViewById<Button>(R.id.registerbutton)
        buttonClick2.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }
    }
}