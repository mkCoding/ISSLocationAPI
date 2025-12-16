## Description
Created project that gets the latest location of the ISS satalite ever 1 second and presents it to the UI

## what I learned
- How to get latest updates and continuously update UI screen
- HTTP vs HTTPS apis have different permissions by default
- In Android Studio HTTPS (encrypted) is allowed but HTTP (unencrypted) is blocked unless explicitly permitted
- HTTP API calls require cleartext traffic in Manifest
- Polling: repeatedly asking the API for updated data at a set interval.
- To poll data you can use either this: **while (currentCoroutineContext().isActive)**  or this **while(true)**

## HTTP API calls requre usesCleartextTraffic in AndroidManifest file
  ```
<application
    android:usesCleartextTraffic="true"
  ```

## Also if you need to poll your api you would need to do this in your viewModel

```
    fun pollISSLocation(){
        viewModelScope.launch {
            // this while loop uses inActive which returns true when coroutine is alive
            // used for polling or getting periodic update from api call where data is constantly changing
            while (currentCoroutineContext().isActive) {
                try {
                    val data = repository.getISSLocation()
                    _uiState.value = ISSLocationState.Success(data)
                } catch (e: Exception) {
                    _uiState.value = ISSLocationState.Error(e.message ?: "Unknown error")
                }
                delay(1000)
            }

        }
    }

```
