package com.example.satelliteapiconsumption.data.api

import com.example.satelliteapiconsumption.data.model.ISSModel
import retrofit2.http.GET

interface ISSLocationApi {

    @GET(ApiDetails.ENDPOINT_URL)
    suspend fun getISSLocation(): ISSModel
}