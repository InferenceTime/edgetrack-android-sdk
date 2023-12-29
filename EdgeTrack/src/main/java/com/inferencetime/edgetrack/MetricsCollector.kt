package com.inferencetime.edgetrack

import com.inferencetime.edgetrack.internal.models.PerformanceMetrics
import com.inferencetime.edgetrack.internal.models.PerformanceMetricsRequest
import com.inferencetime.edgetrack.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.LinkedList
import java.util.Queue

class MetricsCollector(
    private val edgeTrack: EdgeTrack,
    private val networkService: NetworkService
) {
    private val metricsQueue: Queue<PerformanceMetrics> = LinkedList()


    init {
        startSendingMetrics()
    }

    fun collectMetrics(metrics: PerformanceMetrics) {
        metricsQueue.add(metrics)
    }

    private fun startSendingMetrics() {
        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                if (metricsQueue.isNotEmpty()) {
                    val batch = mutableListOf<PerformanceMetrics>()
                    // Adjust batch size based on the number of metrics
                    val batchSize = minOf(MAX_BATCH_SIZE, metricsQueue.size)

                    for (i in 0 until batchSize) {
                        metricsQueue.poll()?.let { batch.add(it) }
                    }
                    sendMetricsToBackend(batch)
                }
                delay(10_000L) // Send data every 10 to 20 seconds
            }
        }
    }

    private suspend fun sendMetricsToBackend(metrics: List<PerformanceMetrics>) {
        networkService.sendMetrics(PerformanceMetricsRequest(metrics))
    }

    companion object {
        private const val MAX_BATCH_SIZE = 50
    }
}