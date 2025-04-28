package com.shnoozie.fuzzyio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shnoozie.fuzzyio.model.NoiseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NoiseGeneratorViewModel: ViewModel() {

    val stateFlow: MutableStateFlow<NoiseState> = MutableStateFlow(NoiseState())

    val noiseGenerator = NoiseGenerator()

    fun toggleNoiseGeneration() {
        noiseGenerator.playWhiteNoise(5000)
        viewModelScope.launch {
            stateFlow.value = stateFlow.value.copy(
                isPlaying = !stateFlow.value.isPlaying,
                playingButtonText = if (!stateFlow.value.isPlaying) {
                    "On"
                } else {
                    "Off"
                }
            )
        }
    }

    fun modifyNoise() {

    }

}