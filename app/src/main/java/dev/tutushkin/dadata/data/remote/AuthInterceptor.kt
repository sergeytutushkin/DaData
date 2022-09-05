package dev.tutushkin.dadata.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val request = originalRequest.newBuilder()
            .header("Authorization", "c8ccd98be6af011018583caede2c5546f1e1954b")
            .build()

        return chain.proceed(request)
    }
}
