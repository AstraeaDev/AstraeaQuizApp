package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private lateinit var fireBaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fireBaseAuth = FirebaseAuth.getInstance()

        val buttonClick = findViewById<Button>(R.id.login_button)

        buttonClick.setOnClickListener {

            val email = findViewById<EditText>(R.id.login_email_address).text.toString()
            //val email = binding.registerEmail.text.toString()
            val password = findViewById<EditText>(R.id.login_password).text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                fireBaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(){
                    if(it.isSuccessful){
                        val intent = Intent(this,MainPage::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()

                    }

                }
            }
            else {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_LONG).show()
            }
            /* val intent = Intent(this, MainPage::class.java)
             startActivity(intent)

             */
        }


        val buttonClick2 = findViewById<Button>(R.id.registerbutton)
        buttonClick2.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }
    }
}