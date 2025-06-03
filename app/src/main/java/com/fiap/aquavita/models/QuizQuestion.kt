package com.fiap.aquavita.models

data class QuizQuestion(
    val statement: String,
    val correct: Boolean,
    val explanation: String
)