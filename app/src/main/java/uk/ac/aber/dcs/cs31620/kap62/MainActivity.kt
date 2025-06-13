package uk.ac.aber.dcs.cs31620.kap62

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.aber.dcs.cs31620.kap62.ui.theme.Kap62Theme

/*
* Author : @kap62
* This is my CS31620 Assignment App, all code written and annotated by me.
* */


// Main activity that runs the actual App
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kap62Theme {
                MainScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Kap62Theme {
        MainScreen()
    }
}
