package uk.ac.aber.dcs.cs31620.kap62

enum class QuestionType { MultipleChoice, TextEntry }


// Simple question data class
data class Question(
    val text: String,
    val options: List<String> = emptyList(),
    val correctAnswer: String,
    val type: QuestionType = QuestionType.MultipleChoice
)