package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        name = intent.getStringExtra("userName").toString()
        if (name == "null") {
            editText.setText("")
        } else {
            editText.setText(name)
        }


        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (editText.text.isEmpty()) {
                    Toast.makeText(this@MainActivity,"Please fill your name", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("userName", editText.text.toString())
                    startActivity(intent)
                    finish()
                }

            }

        })
    }
}