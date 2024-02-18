package com.inferencetime.edgetrack.network

import com.inferencetime.edgetrack.storage.Cache
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", Cache.getApiKey() ?: "")
            .build()
        return chain.proceed(newRequest)
    }
}