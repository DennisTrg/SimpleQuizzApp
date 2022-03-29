package com.example.quizz

object Constants {
    fun getQuest(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        val question1 = Question(
            1,
            "How can we force-unwrap in Android?",
            "?",
            "!!",
            "!",
            2)
        questionList.add(question1)

        val question2 = Question(
            2,
            "What is the suitable IDE for Android?",
            "Android Studio",
            "Xcode",
            "Both Above Answer",
            1)
        questionList.add(question2)

        val question3 = Question(
            3,
            "What is the correct way to create a function in Kotlin?",
            "func variableName()",
            "function variableName()",
            "fun variableName()",
            3)
        questionList.add(question3)

        val question4 = Question(
            4,
            "What is the correct number of Life Cycle State in Android?",
            "3",
            "4",
            "5",
            2)
        questionList.add(question4)

        val question5 = Question(
            5,
            "How many callbacks functions exist in Activity class?",
            "6",
            "7",
            "4",
            1)
        questionList.add(question5)

        return questionList
    }
}