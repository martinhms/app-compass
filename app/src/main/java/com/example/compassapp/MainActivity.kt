package com.example.compassapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compassapp.ui.CompassViewModel
import com.example.compassapp.ui.theme.CompassAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val compassViewModel: CompassViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompassAppTheme {
                CompassScreen(compassViewModel)
            }
        }
    }
}

@Composable
fun CompassScreen(compassViewModel: CompassViewModel) {
    var textInputUI by remember { mutableStateOf("") }
    val textOutput: String  by compassViewModel.resultText.observeAsState("")

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textOutput,
            modifier = Modifier
                .padding(20.dp)
                .wrapContentHeight()
        )

        TextField(
            value = textInputUI, onValueChange = { textInputUI = it}, singleLine = false,
            maxLines = 10,
            modifier = Modifier
                .padding(20.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        )
        Button(
            onClick = {
                compassViewModel.onRequestRunned(textInputUI)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = "Run requests")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompassAppTheme {
        //CompassScreen(compassViewModel)
    }
}