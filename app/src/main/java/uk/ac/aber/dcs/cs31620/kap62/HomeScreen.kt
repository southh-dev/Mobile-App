package uk.ac.aber.dcs.cs31620.kap62

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/*
* Author : @kap62
* This is my CS31620 Assignment App, all code written and annotated by me.
* */

// This is the home screen
@Composable
fun HomeScreen(

    //Lambda functions to handle click events  and the other variables, like listing the questions or deleteing the queswtions
    onQuizClick: () -> Unit,
    onCreateQuestionClick: () -> Unit,
    questionList: List<Question>,
    onDeleteQuestion: (Question) -> Unit
)

{
    // organised it all as a column
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            )
            {
                // All of this in order is what presents on the home screen, including the quiz button which begins a quiz, the create question button
                // which lets the user select a type of question to create, and the question list which displays current questions

                // This is the quiz image, so the book stack that i got, it's just there to add some flair to the home screen
                Image(
                    painter = painterResource(id = R.drawable.quiz_image),
                    contentDescription = "Quiz Image",
                    modifier = Modifier.size(150.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onQuizClick,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                {
                    Text(text = "QUIZ")
                }

                Button(onClick = onCreateQuestionClick) {
                    Text(text = "CREATE QUESTION")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Questions List", style = MaterialTheme.typography.headlineMedium)

                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                )
                {
                    LazyColumn {
                        items(questionList) { question ->
                            QuestionRow(question = question, onDelete = { onDeleteQuestion(question) })
                        }
                    }
                }
            }
        }
    }
}