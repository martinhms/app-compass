package com.example.compassapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.compassapp.R

@Composable
fun Compass(compassViewModel: CompassViewModel) {
    val textInputState by compassViewModel.textInput.observeAsState("")
    val textEvery10OutputState: String by compassViewModel.resultEvery10Text.observeAsState("")
    val textWCOutputState: String by compassViewModel.resultWCText.observeAsState("")

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.compass_logo_black),
            contentDescription = "Logo de la app",
            modifier = Modifier
                .size(150.dp)
        )
        Every10OutputComponnent(textEvery10OutputState)
        Spacer(modifier = Modifier.padding(10.dp))
        WordCCounterComponent(textWCOutputState)
        Spacer(modifier = Modifier.padding(10.dp))
        InputsComponent(
            textInputState = textInputState,
            onTextInputChanged = { compassViewModel.onTextInputChanged(it) },
            onRequestRunned = { compassViewModel.onRequestRunned() }
        )
    }
}

@Composable
fun Every10OutputComponnent(textEvery10OutputState: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = "Every10thCharacter Result:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black

        )
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.Black
        )
        Text(
            text = textEvery10OutputState,
            modifier = Modifier
                .padding(20.dp)
                .wrapContentHeight(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Composable
fun WordCCounterComponent(textWCOutputState: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = "WordCounter Result:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        HorizontalDivider(
            thickness = 2.dp,
            color = Color.Black
        )
        Text(
            text = textWCOutputState,
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@Composable
fun InputsComponent(
    textInputState: String,
    onTextInputChanged: (String) -> Unit,
    onRequestRunned: () -> Unit
) {
    TextField(
        value = textInputState,
        onValueChange = { onTextInputChanged(it) },
        singleLine = false,
        maxLines = 10,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        placeholder = {
            Text(
                "Enter the text",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black), // Cambia el color del hint
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
        },
    )
    Button(
        onClick = {
            onRequestRunned()
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        colors = ButtonColors(
            containerColor = Color.Black,
            disabledContentColor = Color.LightGray,
            contentColor = Color.White,
            disabledContainerColor = Color.White
        )
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Run requests",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            "Run",
            style = MaterialTheme.typography.titleLarge
        )
    }
}