package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.astraeaquizapp.databinding.ActivityRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterPage : AppCompatActivity() {


    private lateinit var fireBaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        fireBaseAuth = FirebaseAuth.getInstance()


        val buttonClick = findViewById<Button>(R.id.register_button)
        buttonClick.setOnClickListener {

            val email = findViewById<EditText>(R.id.register_email).text.toString()
            //val email = binding.registerEmail.text.toString()
            val password = findViewById<EditText>(R.id.register_password).text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                fireBaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()

                        }

                    }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_LONG).show()
            }

        }

    }
}