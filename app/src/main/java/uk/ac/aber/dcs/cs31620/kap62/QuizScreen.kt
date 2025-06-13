package uk.ac.aber.dcs.cs31620.kap62

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
* Author : @kap62
* This is my CS31620 Assignment App, all code written and annotated by me
*/

@Composable
fun QuizScreen(onBack: () -> Unit, questions: List<Question>) {

    // Setting up variables for the quiz screen

    val scope = rememberCoroutineScope()
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var showFeedback by remember { mutableStateOf(false) }
    var feedbackMessage by remember { mutableStateOf("") }
    var userAnswer by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }

    // Short little bit of text that appears if there are no questions present for the user to interact with, it's more intended as something for an error
    if (questions.isEmpty()) {
        Text("No questions available")
        return
    }

    val question = questions[currentQuestionIndex]

    // Setting up the column for the rest of the screen, everything will sit inside of this column
    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {
        //Score text, held at the top of the screen, simple text to show the user what score they have thus far
        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // This is the text for the user to understand what the question is
        Text(
            text = question.text,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Simple script to set the feedback message as well as its properties, if the feedback is
        // CORRECT then it'll appear as green, if it is anything else (incorrect) it'll appear red
        if (showFeedback) {
            Text(
                text = feedbackMessage,
                style = MaterialTheme.typography.headlineMedium,
                color = if (feedbackMessage == "Correct!") Color.Green else Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        //Setting up the correct/incorrect answers and settings for a multiple choice question
        if (question.type == QuestionType.MultipleChoice) {
            question.options.forEach { option ->
                Button(
                    onClick = {
                        if (option == question.correctAnswer) {
                            feedbackMessage = "Correct!"
                            score += 1
                            showFeedback = true
                            scope.launch {
                                delay(1000L)
                                showFeedback = false
                                currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
                            }
                        } else {
                            feedbackMessage = "Incorrect. Try again."
                            showFeedback = true
                            scope.launch {
                                delay(1000L)
                                showFeedback = false
                                // Originally i had the user fail the question and get another opportunity to pick the right one, but i decided to have them move on regardless
                                currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = option)
                }
            }
        } else {
            // Elsee setting up feedback for text field questions
            BasicTextField(
                value = userAnswer,
                onValueChange = { userAnswer = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .background(Color.LightGray)
            )
            Button(
                onClick = {
                    if (userAnswer == question.correctAnswer) {
                        feedbackMessage = "Correct!"
                        score += 1
                        showFeedback = true
                        scope.launch {
                            delay(1000L)
                            showFeedback = false
                            currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
                            userAnswer = ""
                        }
                    } else {
                        feedbackMessage = "Incorrect. Try again."
                        showFeedback = true
                        scope.launch {
                            delay(1000L)
                            showFeedback = false
                            currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
                        }
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Submit")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            //Exit button
            Text(text = "Back to Home")
        }
    }
}