package com.inferencetime.edgetrack.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Token 1234567890")
            .build()
        return chain.proceed(newRequest)
    }
}