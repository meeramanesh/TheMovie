package com.meera.themovie.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.meera.themovie.configuration.MovieConfiguration
import com.meera.themovie.data.remote.api.MovieApiService
import com.meera.themovie.data.remote.intercepter.RequestIntercepter
import com.meera.themovie.data.repository.MovieRepository
import com.meera.themovie.schedulers.BaseScheduler
import com.meera.themovie.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * File Description
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
@Module(includes = [(ViewModelModule::class)])
class ApiModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addNetworkInterceptor(RequestIntercepter())
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(MovieConfiguration.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApiService, scheduler: BaseScheduler): MovieRepository {
        return MovieRepository(api, scheduler)
    }

    @Provides
    @Singleton
    fun provideScheduler(): BaseScheduler {
        return SchedulerProvider()
    }

}