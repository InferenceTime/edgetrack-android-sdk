package com.inferencetime.edgetrack.network

import com.inferencetime.edgetrack.internal.models.PerformanceMetricsRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkService {
    @POST("/api/v1/collect-metrics/")
    suspend fun sendMetrics(@Body metrics: PerformanceMetricsRequest)
}