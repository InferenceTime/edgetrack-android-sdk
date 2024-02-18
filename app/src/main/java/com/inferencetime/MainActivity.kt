package com.inferencetime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.inferencetime.edgetrack.EdgeTrack
import com.inferencetime.edgetrack.models.EdgeTrackMetrics

class MainActivity : AppCompatActivity() {
    private lateinit var et: EdgeTrack
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val collectMetricButton = findViewById<Button>(R.id.collectMetricButton)
        collectMetricButton.setOnClickListener {
            collectMetrics()
        }

        et = EdgeTrack(
            apiKey = "YOUR_API_KEY",
            sessionUuid = "Session123",
            context = this
        )
    }

    private fun collectMetrics() {
        val randomInference = (40..100).random().toFloat()

        et.collectMetrics(
            EdgeTrackMetrics(
                modelName = "my_test_model",
                modelVersion = "1.0",
                inferenceTime = randomInference,
                modelAccuracy = 0.95f
            )
        )
    }
}