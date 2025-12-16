package com.example.satelliteapiconsumption.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.satelliteapiconsumption.data.model.ISSModel
import com.example.satelliteapiconsumption.data.model.IssPositionModel

@Composable
fun IssLocationScreen(
    state: ISSLocationState
) {

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ISS Current Location",
            fontSize = 35.sp,
            modifier = Modifier.padding(16.dp)
        )
        
        when(state){
            is ISSLocationState.Loading -> {
                CircularProgressIndicator()
            }
            is ISSLocationState.Success -> {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White, // Set the desired background color
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(8.dp)
                ) {
                    var longitude = state.data.iss_position?.longitude?:"Issue retrieving data"
                    var latitude = state.data.iss_position?.latitude?:"Issue retrieving data"
                    val timeStamp = state.data.timestamp
                    Box(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column() {
                            // Timestamp
                            Text("Timestamp: $timeStamp", fontSize = 25.sp)
                            // Longitude
                            Text(text = "Longitude: $longitude", fontSize = 25.sp )

                            // Latitude
                            Text("Latitude: $latitude", fontSize = 25.sp)
                        }

                    }


                }
            }
            is ISSLocationState.Error -> {
                Text("Error retrieving data")
            }
        }

        

    }

}


@Preview(showBackground = true)
@Composable
fun ISSLocationScreenPreview() {
    IssLocationScreen(
        ISSLocationState.Success(
            ISSModel(
                IssPositionModel(
                    latitude = "4847485.493",
                    longitude = "-101.3560"
                ),
                message = "successful",
                timestamp = 1765920579
            )
        )
    )
}

/*
   "longitude": "-101.3560",
    "latitude": "-6.1966"
 */