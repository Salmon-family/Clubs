package com.devfalah.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DdosInterceptor @Inject constructor(): Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        requestsHashCodeBuffer.add(0, request.hashCode())
        while (requestsHashCodeBuffer.lastOrNull() != request.hashCode()){
            runBlocking {
                delay(DELAY_PERIOD)
            }
        }
        return try {
            chain.proceed(request).also {
                requestsHashCodeBuffer.removeLast()
            }
        }catch (e: Exception) {
            requestsHashCodeBuffer.removeLast()
            chain.proceed(request)
        }
    }

    companion object {
        private const val DELAY_PERIOD = 1500L
        private var requestsHashCodeBuffer = mutableListOf<Int>()
    }
}