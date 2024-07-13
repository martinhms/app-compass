package com.example.compassapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    val textInputState by compassViewModel.textInput.observeAsState("")
    val textEvery10OutputState: String by compassViewModel.resultEvery10Text.observeAsState("")
    val textWCOutputState: String by compassViewModel.resultWCText.observeAsState("")

     Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "Every10thCharacter Result:",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = textEvery10OutputState,
                modifier = Modifier
                    .padding(20.dp)
                    .wrapContentHeight(),
                style = MaterialTheme.typography.bodyMedium

            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "WordCounter Result:",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = textWCOutputState,
                modifier = Modifier
                    .padding(20.dp)
                    .wrapContentHeight(),
            )
        }

        TextField(
            value = textInputState, onValueChange = { compassViewModel.onTextInputChanged(it) }, singleLine = false,
            maxLines = 10,
            modifier = Modifier
                .padding(20.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        )
        Button(
            onClick = {
                compassViewModel.onRequestRunned()
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