package com.example.quizz

data class Question (
    val id: Int,
    val question: String,
    val firstAnswer: String,
    val secondAnswer: String,
    val thirdAnswer: String,
    val correctAnswer: Int
    )