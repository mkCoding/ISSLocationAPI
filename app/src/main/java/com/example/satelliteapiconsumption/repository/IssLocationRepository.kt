package com.example.satelliteapiconsumption.repository

import com.example.satelliteapiconsumption.data.api.ISSLocationApi
import javax.inject.Inject

class IssLocationRepository @Inject constructor(
    private val issLocationApi: ISSLocationApi
){
    suspend fun getISSLocation() = issLocationApi.getISSLocation()
}