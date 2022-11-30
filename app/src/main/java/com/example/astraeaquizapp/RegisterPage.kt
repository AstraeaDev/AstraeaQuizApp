package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.astraeaquizapp.databinding.ActivityRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterPage : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterPageBinding
    private lateinit var fireBaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fireBaseAuth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener(){

            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                fireBaseAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(){
                    if(it.isSuccessful){
                        val intent = Intent(this,SignIn::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(),Toast.LENGTH_LONG).show()

                    }

                }
            }
            else {
                Toast.makeText(this, "Empty Fields Are not Allowed",Toast.LENGTH_LONG).show()
            }



        }




        /*

        val buttonClick = findViewById<Button>(R.id.register_button)
        buttonClick.setOnClickListener {
            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)

            //if you registered sign in
            //if you dont sign up

         */

        }
    }
