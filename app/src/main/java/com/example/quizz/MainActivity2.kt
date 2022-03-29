package com.example.quizz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private var currentPosition: Int = 1
    private var questionList : ArrayList<Question>? = null
    private var selectedOption: Int = 0
    private var correctAnswer: Int = 0
    private var welcomeText : String? = null
    lateinit var textView: TextView
    lateinit var progressBar: ProgressBar
    lateinit var textViewProgressBar: TextView
    lateinit var textViewQuestionIndex: TextView
    lateinit var textViewQuestionContent: TextView
    lateinit var firstAnswer: TextView
    lateinit var secondAnswer: TextView
    lateinit var thirdAnswer: TextView
    lateinit var submitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        val welcomeText : String = intent.getStringExtra("userName").toString()

        textView = findViewById<TextView>(R.id.textView3)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        textViewProgressBar = findViewById<TextView>(R.id.textView4)
        textViewQuestionIndex = findViewById<TextView>(R.id.textView5)
        textViewQuestionContent = findViewById<TextView>(R.id.textView6)
        firstAnswer = findViewById<TextView>(R.id.textViewFirstAnswer)
        secondAnswer = findViewById<TextView>(R.id.textViewSecondAnswer)
        thirdAnswer = findViewById<TextView>(R.id.textViewThirdAnswer)
        submitButton = findViewById<Button>(R.id.button5)

        //set welcome text
        welcomeText = intent.getStringExtra("userName").toString()
        textView.text = "Welcome " + welcomeText
        questionList = Constants.getQuest()
        setUpQuestion()
        firstAnswer.setOnClickListener(this)
        secondAnswer.setOnClickListener(this)
        thirdAnswer.setOnClickListener(this)
        submitButton.setOnClickListener(this)

    }

    private fun setUpQuestion(){
        if (currentPosition >1) {
            textView.text = ""
        }
        val question = questionList!![currentPosition - 1]
        defaultOptionsView()

        if (currentPosition == questionList!!.size) {
            submitButton.text = "Finish"
        } else {
            submitButton.text = "Submit"
        }

        //set progressbar
        progressBar.setProgress(currentPosition)
        //set textView progressBar count
        textViewProgressBar.text = "$currentPosition" + "/" + progressBar.max
        //
        textViewQuestionIndex.text = "Question " + currentPosition.toString()


        if (question != null) {
            textViewQuestionContent.text = question.question
            firstAnswer.text = question.firstAnswer
            secondAnswer.text = question.secondAnswer
            thirdAnswer.text = question.thirdAnswer

        } else {
            return
        }
    }

    private fun defaultOptionsView(){
        var options = ArrayList<TextView>()
        options.add(0,firstAnswer)
        options.add(1, secondAnswer)
        options.add(2, thirdAnswer)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_state)
        }
    }

    private fun selectedOptionView(textView: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        selectedOption = selectedOptionNumber
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option)
    }

    private fun answerView(answer: Int, drawColorView: Int) {
        when(answer) {
            1 -> {
                firstAnswer.background = ContextCompat.getDrawable(this,drawColorView)
            }
            2 -> {
                secondAnswer.background = ContextCompat.getDrawable(this, drawColorView)
            }
            3 -> {
                thirdAnswer.background = ContextCompat.getDrawable(this, drawColorView)
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.textViewFirstAnswer -> {
                selectedOptionView(firstAnswer,1)
            }
            R.id.textViewSecondAnswer -> {
                selectedOptionView(secondAnswer, 2)
            }
            R.id.textViewThirdAnswer -> {
                selectedOptionView(thirdAnswer, 3)
            }
            R.id.button5 -> {
                if (selectedOption == 0) {
                    currentPosition++
                    when{
                        currentPosition <= questionList!!.size -> {
                            setUpQuestion()
                        } else -> {
                        //Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,ResultActivity::class.java)
                        intent.putExtra("correctScore", correctAnswer.toString())
                        intent.putExtra("userName", welcomeText)
                        startActivity(intent)
                        finish()
                        }
                    }
                } else {
                    val question = questionList?.get(currentPosition - 1)
                    if (question!!.correctAnswer != selectedOption) {
                        answerView(selectedOption, R.drawable.wrong_option_state)
                    } else {
                        correctAnswer++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_state)

                    if (currentPosition == questionList!!.size) {
                        submitButton.text = "Finish"
                    } else {
                        submitButton.text = "Next"
                    }
                    selectedOption = 0
                }

            }
        }
    }
}