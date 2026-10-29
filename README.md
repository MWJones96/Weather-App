# Weather-App

Made by Matthew Jones

An Android weather application where the user can select a latitude and longitude pair using the Google Maps API and be presented with a 5-day forecast for that area.

## Overview

On launch the app looks up the device's last known location and downloads a forecast for it from the OpenWeatherMap 5 day / 3 hour forecast API. The user can switch between a two-day and a five-day view, or pick a different location by tapping on a Google Map. The app was written in 2017 with Android Studio.

## Features

- Forecast for the device's current location on start-up.
- **Two Day Forecast** view (today and tomorrow): weather icon, temperature in °C, description, humidity and wind speed.
- **Five Day Forecast** view: date, weather icon, temperature in °C and humidity for each day.
- **Change Location** menu option that opens a Google Map centred on the current location; tapping anywhere on the map reloads the forecast for that point.
- Day and night weather icons matched to the OpenWeatherMap icon codes.

## Tech stack

- Java, Android SDK (compile and target SDK 25, minimum SDK 15)
- Android Support Library (`appcompat-v7` 25.2.0, `constraint-layout` 1.0.2)
- Google Play services Maps (`play-services-maps` 10.2.0)
- OpenWeatherMap forecast API, fetched with `HttpURLConnection` in an `AsyncTask` and parsed with `org.json`
- Gradle 3.3 (wrapper) with Android Gradle plugin 2.3.0

## Getting started

### Prerequisites

- An Android SDK with API level 25 and build tools 25.0.2.
- An Android Studio version that supports Android Gradle plugin 2.3.0 and Gradle 3.3 (a 2017-era release), or be prepared to upgrade the build files (see Limitations).
- A Google Maps Android API key and an OpenWeatherMap API key of your own.

### Configure API keys

The repository has API keys committed in source. Do not rely on them: supply your own.

- **Google Maps**: put your key in the `google_maps_key` string in `app/src/debug/res/values/google_maps_api.xml` (debug builds) and `app/src/release/res/values/google_maps_api.xml` (release builds).
- **OpenWeatherMap**: replace the `APPID` value in the forecast URL in `app/src/main/java/com/example/matthew/weatherforecast/MainActivity.java`.

### Build and run

Open the project in Android Studio and run the `app` configuration, or use the Gradle wrapper:

```sh
./gradlew assembleDebug      # build the debug APK
./gradlew installDebug       # install it on a connected device or emulator
```

The device or emulator needs Google Play services, network access and a last known location.

## Usage

1. Launch **Weather Forecast**. The location and date are shown at the top.
2. Tap **Two Day Forecast** or **Five Day Forecast** to show that view.
3. Choose **Change Location** from the options menu, then tap a point on the map to load its forecast.

## Project structure

```
app/build.gradle                                 Module build: SDK versions and dependencies
app/src/main/AndroidManifest.xml                 Activities, permissions, Maps key meta-data
app/src/main/java/com/example/matthew/weatherforecast/
  MainActivity.java       Gets the location, downloads the forecast, switches fragments, options menu
  MapsActivity.java       Google Map; a tap returns the chosen latitude/longitude to MainActivity
  DownloadTask.java       AsyncTask that downloads a URL as a string
  WeatherTask.java        Holds the forecast JSON and the two fragments
  TwoDayForecast.java     Two-day forecast fragment
  FiveDayForecast.java    Five-day forecast fragment
app/src/main/res/         Layouts, menu and day/night weather icons
app/src/debug|release/res/values/google_maps_api.xml   Google Maps API key per build type
```

The `test` and `androidTest` folders contain only the default Android Studio example tests.

## Limitations

- The build uses Android Gradle plugin 2.3.0, Gradle 3.3 and the `jcenter()` repository, which has since been shut down. Building with a current Android Studio needs the Gradle setup and dependencies upgraded (for example to AndroidX and current Play services).
- The app does not request location permission at runtime. On Android 6.0 and later, grant location permission in system settings first, otherwise no forecast is loaded. It also expects a last known location to be available.
- The forecast is requested over plain HTTP from the OpenWeatherMap 2.5 API. The service and its terms may have changed since the app was written, and a key is required.
- Each day's figures are taken from one 3-hour forecast entry per 24 hours, not a daily summary.
- Wind speed is labelled "mph", but the API returns metres per second by default.
- Network and parsing errors are silently ignored.

## Acknowledgements

- Weather data from [OpenWeatherMap](https://openweathermap.org/).
- Maps from the [Google Maps Android API](https://developers.google.com/maps/documentation/android-sdk).
