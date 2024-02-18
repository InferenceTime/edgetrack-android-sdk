package com.inferencetime.edgetrack.models

data class EdgeTrackMetrics(
    val modelName: String,
    val modelVersion: String,
    val inferenceTime: Float,
    val modelAccuracy: Float? = 0f,
)
