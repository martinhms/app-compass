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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compassapp.ui.theme.CompassAppTheme
import com.example.compassapp.ui.theme.CompassViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val compassViewModel : CompassViewModel by viewModels()

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
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth().padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Results",
            modifier = Modifier
                .padding(20.dp)
                .wrapContentHeight()
        )

        TextField(
            value = "Enter text", onValueChange = { }, singleLine = false,
            maxLines = 10,
            modifier = Modifier
                .padding(20.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        )
        Button(
            onClick = {
                // onTaskAdded(task)
                //  task = ""
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