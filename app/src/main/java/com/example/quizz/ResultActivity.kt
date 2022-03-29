package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var congratTextView: TextView
    lateinit var scoreTextView: TextView
    lateinit var takeNewQuizzButton: Button
    lateinit var finishButton: Button
    private var score: String? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        congratTextView = findViewById<TextView>(R.id.congratsTextView)
        scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        takeNewQuizzButton = findViewById<Button>(R.id.takeNewQuizzButton)
        finishButton = findViewById<Button>(R.id.finishButton)

        name = intent.getStringExtra("userName").toString()
        score = intent.getStringExtra("correctScore").toString()
        congratTextView.text = "Congratulations " + name
        scoreTextView.text = score + "/" + "5"

        finishButton.setOnClickListener{
            System.exit(1)
        }

        takeNewQuizzButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent  = Intent(this@ResultActivity, MainActivity::class.java)
                intent.putExtra("userName", name)
                startActivity(intent)
                finish()
            }
        })

    }
}