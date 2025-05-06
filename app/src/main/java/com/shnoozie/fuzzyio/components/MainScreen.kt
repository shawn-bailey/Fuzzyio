package com.shnoozie.fuzzyio.components

import android.widget.Button
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shnoozie.fuzzyio.NoiseGeneratorViewModel
import com.shnoozie.fuzzyio.R
import com.shnoozie.fuzzyio.model.NoiseEvent
import com.shnoozie.fuzzyio.model.NoiseState

@Composable
fun MainScreen(noiseGeneratorViewModel: NoiseGeneratorViewModel = hiltViewModel()) {

    val noiseState by noiseGeneratorViewModel.stateFlow.collectAsState()

    SettingsButton()
    NoiseButton(noiseState, noiseGeneratorViewModel::toggleNoiseGeneration)
}

@Composable
fun SettingsButton(modifier: Modifier = Modifier) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.settings_icon),
            contentDescription = "Book cover",
            modifier = Modifier
                .clickable(onClick = {})
                .size(30.dp)
                .padding(4.dp)
                .background(MaterialTheme.colorScheme.surface)
        )
        Text(text = "Settings")
    }
}

@Composable
fun NoiseButton(
    state: NoiseState,
    toggleState: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "White Noise Generator",
            modifier = modifier
        )
        Button(
            onClick = toggleState
        ) {
            Text(state.playingButtonText)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NoiseButtonPreview(modifier: Modifier = Modifier) {
    NoiseButton(NoiseState())
}

@Composable
@Preview(showBackground = true)
fun NoiseButtonPreviewOff(modifier: Modifier = Modifier) {
    NoiseButton(NoiseState(playingButtonText = "Off"))
}

@Composable
@Preview(showBackground = true)
fun SettingButtonPreview(modifier: Modifier = Modifier) {
    SettingsButton()
}