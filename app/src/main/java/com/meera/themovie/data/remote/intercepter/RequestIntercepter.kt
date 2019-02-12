package com.meera.themovie.data.remote.intercepter

import com.meera.themovie.configuration.MovieConfiguration
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * RequestIntercepter to add in the network request
 * API Key should be added as teh Query parameter in each request
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
class RequestIntercepter : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", MovieConfiguration.API_KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
