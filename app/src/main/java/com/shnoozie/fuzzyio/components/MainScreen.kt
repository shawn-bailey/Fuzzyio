package com.shnoozie.fuzzyio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.shnoozie.fuzzyio.NoiseGeneratorViewModel
import com.shnoozie.fuzzyio.model.NoiseEvent
import com.shnoozie.fuzzyio.model.NoiseState

@Composable
fun MainScreen(noiseGeneratorViewModel: NoiseGeneratorViewModel = hiltViewModel()) {

    val noiseState by noiseGeneratorViewModel.stateFlow.collectAsState()

    NoiseButton(noiseState, noiseGeneratorViewModel::toggleNoiseGeneration)
}

@Composable
fun NoiseButton(
    state: NoiseState,
    toggleState: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column {
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