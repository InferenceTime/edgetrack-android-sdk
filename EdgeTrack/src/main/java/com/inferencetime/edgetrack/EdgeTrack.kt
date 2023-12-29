package com.inferencetime.edgetrack

import android.content.Context
import android.util.Log
import com.inferencetime.edgetrack.internal.models.PerformanceMetrics
import com.inferencetime.edgetrack.models.EdgeTrackMetrics
import com.inferencetime.edgetrack.network.NetworkClient
import com.inferencetime.edgetrack.network.NetworkService
import com.inferencetime.edgetrack.storage.Cache

class EdgeTrack(
    private val apiKey: String,
    private val sessionUuid: String,
    private val context: Context
) {
    private val networkService: NetworkService = NetworkClient.createNetworkService()
    private val metricsCollector: MetricsCollector = MetricsCollector(this, networkService)
    private val deviceModel = android.os.Build.MODEL
    private val deviceOSVersion = android.os.Build.VERSION.RELEASE
    private val deviceAPIVersion = android.os.Build.VERSION.SDK_INT
    private val deviceCPUArchitecture = android.os.Build.SUPPORTED_ABIS[0]

    init {
        Cache.init(context)
        Cache.saveString("apiKey", apiKey)
        Cache.saveString("sessionUuid", sessionUuid)
        val info = getDeviceInfo()
        Log.d("Device Info:", info)

    }

    /**
     * Collects performance metrics
     */
    fun collectMetrics(metrics: EdgeTrackMetrics) {
        // generate the PerformanceMetrics object
        // add it to the queue

        // device hardware information
        val deviceCPUCores = Runtime.getRuntime().availableProcessors()
        val deviceTotalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024
        val deviceAvailableMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024
        val deviceMaxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024

        val performanceMetrics = PerformanceMetrics(
            sessionId = sessionUuid,
            modelName = metrics.modelName,
            modelVersion = metrics.modelVersion,
            inferenceTime = metrics.inferenceTime,
            modelAccuracy = metrics.modelAccuracy,
            deviceModel = deviceModel,
            osVersion = deviceOSVersion,
            apiLevel = deviceAPIVersion,
            cpuArchitecture = deviceCPUArchitecture,
            cpuCores = deviceCPUCores,
            totalMemory = deviceTotalMemory,
            availableMemory = deviceAvailableMemory,
            maxMemory = deviceMaxMemory,
            timestamp = System.currentTimeMillis()
        )

        metricsCollector.collectMetrics(performanceMetrics)
    }

    /**
     * Get static device information like device model, OS version, etc.
     */
    private fun getDeviceInfo(): String {
        return "Device Model: ${android.os.Build.MODEL}\n" +
                "OS Version: ${android.os.Build.VERSION.RELEASE}\n" +
                "API Level: ${android.os.Build.VERSION.SDK_INT}" +
                "CPU Architecture: ${android.os.Build.SUPPORTED_ABIS[0]}" +
                "CPU Cores: ${Runtime.getRuntime().availableProcessors()}" +
                "Total Memory: ${Runtime.getRuntime().totalMemory() / 1024 / 1024} MB" +
                "Available Memory: ${Runtime.getRuntime().freeMemory() / 1024 / 1024} MB" +
                "Max Memory: ${Runtime.getRuntime().maxMemory() / 1024 / 1024} MB" +
                "Total Storage: ${context.filesDir.totalSpace / 1024 / 1024} MB" +
                "Available Storage: ${context.filesDir.freeSpace / 1024 / 1024} MB" +
                "Max Storage: ${context.filesDir.usableSpace / 1024 / 1024} MB"
    }
}
