package com.example.astraeaquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.astraeaquizapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var fireBaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fireBaseAuth = FirebaseAuth.getInstance()

        binding.signInBtn.setOnClickListener(){

            val email = binding.mailText.text.toString()
            val password = binding.passwordText.text.toString()

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





        }



    }
}