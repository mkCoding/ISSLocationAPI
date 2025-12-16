package com.example.satelliteapiconsumption.di

import com.example.satelliteapiconsumption.data.api.ApiDetails
import com.example.satelliteapiconsumption.data.api.ISSLocationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesISSLocationApi(retrofit: Retrofit): ISSLocationApi{
        return retrofit.create(ISSLocationApi::class.java)
    }
}