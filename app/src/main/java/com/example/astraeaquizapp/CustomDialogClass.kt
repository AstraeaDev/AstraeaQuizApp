package com.example.astraeaquizapp


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button

class CustomDialogClass(context: Context) : Dialog(context) {

    init {
        setCancelable(false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)

        val buttonClick = findViewById<Button>(R.id.ok_button)
        buttonClick.setOnClickListener {
            dismiss()
        }

    }
}