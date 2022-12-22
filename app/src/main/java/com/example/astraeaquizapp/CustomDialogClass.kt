package com.example.astraeaquizapp


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView

class CustomDialogClass(
    context: Context,
    private var chosenWord: String,
    private var dict: Map<String, String>
) : Dialog(context) {

    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)

        var tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvTitle.setText(chosenWord)

        var tvBody = findViewById<TextView>(R.id.tvBody)
        tvBody.setText("The meaning of $chosenWord is ${dict[chosenWord]}")

        val buttonClick = findViewById<Button>(R.id.ok_button)
        buttonClick.setOnClickListener {
            dismiss()
        }

    }
}