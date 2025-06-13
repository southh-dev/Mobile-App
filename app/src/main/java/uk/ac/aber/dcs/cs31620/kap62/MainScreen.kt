package uk.ac.aber.dcs.cs31620.kap62

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/*
* Author : @kap62
* This is my CS31620 Assignment App, all code written and annotated by me.
* */

@Composable
fun MainScreen() {

    var showQuiz by remember { mutableStateOf(false) }
    var showCreateQuestion by remember { mutableStateOf(false) }

    val questionList by remember { mutableStateOf(

        mutableListOf
            (
            Question("What is the capital of France?", listOf("Paris", "Berlin", "Madrid", "Rome"), "Paris"),
            Question("What is 2 + 2?", listOf("3", "4", "5", "6"), "4"),
            Question("Which planet is known as the Red Planet?", listOf("Earth", "Mars", "Jupiter", "Saturn"), "Mars")
            )
    )
    }

    if (showQuiz) {

        val randomQuestions = questionList.shuffled().take(5)
        QuizScreen(onBack = { showQuiz = false }, questions = randomQuestions)

    }
    else if (showCreateQuestion) {
        CreateQuestionScreen(
            onCreate = { newQuestion ->
                questionList.add(newQuestion)
                showCreateQuestion = false
            },
            onBack = { showCreateQuestion = false }
        )
    }
    else {
        HomeScreen(
            onQuizClick = { showQuiz = true },
            onCreateQuestionClick = { showCreateQuestion = true },
            questionList = questionList,
            onDeleteQuestion = { question -> questionList.remove(question)
            }
        )
    }
}