package com.shnoozie.fuzzyio.model

sealed class NoiseEvent {
    object OnNoiseToggleClick: NoiseEvent()
}