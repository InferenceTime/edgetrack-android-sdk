package com.inferencetime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inferencetime.edgetrack.EdgeTrack
import com.inferencetime.edgetrack.models.EdgeTrackMetrics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et = EdgeTrack("", "", this)
        val metrics = EdgeTrackMetrics(
            modelName = "model_name",
            modelVersion = "model_version",
            inferenceTime = 0.0f,
            modelAccuracy = 0.0f
        )
        et.collectMetrics(metrics)
    }
}