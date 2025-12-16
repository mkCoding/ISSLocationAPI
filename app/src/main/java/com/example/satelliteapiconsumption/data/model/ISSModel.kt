package com.example.satelliteapiconsumption.data.model

data class ISSModel(
    val iss_position: IssPositionModel? = IssPositionModel(),
    val message: String? = "",
    val timestamp: Int? = 0
)