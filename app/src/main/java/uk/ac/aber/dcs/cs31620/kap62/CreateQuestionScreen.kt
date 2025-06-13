package uk.ac.aber.dcs.cs31620.kap62

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
* Author : @kap62
* This is my CS31620 Assignment App, all code written and annotated by me.
* */

@Composable
fun CreateQuestionScreen(onCreate: (Question) -> Unit, onBack: () -> Unit) {

    // State variables, using these i can manage the various input for all of the different question types add fields
    var questionText by remember { mutableStateOf("") }
    var questionType by remember { mutableStateOf(QuestionType.MultipleChoice) }
    var options by remember { mutableStateOf(listOf("", "", "", "")) }
    var correctAnswer by remember { mutableStateOf("") }

    // Column layout again
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        TextField(
            value = questionText,
            onValueChange = { questionText = it },
            label = { Text("Question Text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        var expanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { expanded = true }) {
                Text(text = "Type: ${questionType.name}")
            }
            // Dropdown menu to let the user select which type of question they want to make, whether it's multiple choice or text entry
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                QuestionType.entries.forEach { type ->
                    DropdownMenuItem(onClick = {
                        questionType = type
                        expanded = false
                    }, text = { Text(text = type.name) })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Code to
        if (questionType == QuestionType.MultipleChoice) {
            options.forEachIndexed { index, option ->
                TextField(
                    value = option,
                    onValueChange = { newOption ->
                        options = options.toMutableList().apply { this[index] = newOption }
                    },
                    label = { Text("Option ${index + 1}") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = correctAnswer,
            onValueChange = { correctAnswer = it },
            label = { Text("Correct Answer") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Button that i use to let the user create their question, and ensures it's valid, if it is it'll create the new question.
        Button(
            onClick = {
                if (questionText.isNotEmpty() &&
                    (questionType == QuestionType.TextEntry || options.none { it.isEmpty() }) &&
                    correctAnswer.isNotEmpty()
                ) {
                    onCreate(
                        Question(
                            text = questionText,
                            options = if (questionType == QuestionType.MultipleChoice) options else emptyList(),
                            correctAnswer = correctAnswer,
                            type = questionType
                        )
                    )
                }
            }
        )
        {
            Text(text = "Create Question")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}