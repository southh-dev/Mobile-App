package uk.ac.aber.dcs.cs31620.kap62

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
* Author : @kap62
* This is my CS31620 Assignment App, all code written and annotated by me.
* */


// This is my script to display the existing questions in the question list so the user can review and delete them
@Composable
fun QuestionRow(question: Question, onDelete: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(text = question.text, modifier = Modifier.weight(1f))

        TextButton(
            onClick = onDelete,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "Delete",
                color = Color.White
            )
        }
    }
}