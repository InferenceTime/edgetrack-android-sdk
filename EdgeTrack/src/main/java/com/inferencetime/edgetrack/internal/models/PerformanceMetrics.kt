package com.inferencetime.edgetrack.internal.models

import com.google.gson.annotations.SerializedName


/**
 * Data class for performance metrics
 */
data class PerformanceMetrics(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("model_name")
    val modelName: String,
    @SerializedName("model_version")
    val modelVersion: String,
    @SerializedName("inference_time")
    val inferenceTime: Float,
    @SerializedName("model_accuracy")
    val modelAccuracy: Float,
    @SerializedName("device_model")
    val deviceModel: String,
    @SerializedName("os_version")
    val osVersion: String,
    @SerializedName("api_level")
    val apiLevel: Int,
    @SerializedName("cpu_architecture")
    val cpuArchitecture: String,
    @SerializedName("cpu_cores")
    val cpuCores: Int,
    @SerializedName("total_memory")
    val totalMemory: Long,
    @SerializedName("available_memory")
    val availableMemory: Long,
    @SerializedName("max_memory")
    val maxMemory: Long,
    @SerializedName("timestamp")
    val timestamp: Long
)
