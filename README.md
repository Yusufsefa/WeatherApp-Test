[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)
<a href="https://android-arsenal.com/api?level=21"><img src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" alt="API" /></a>

# WeatherApp-Test

This is a clean and simple weather app for Android developed in Kotlin.

## Libraries used
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Retrofit](https://square.github.io/retrofit/)
* [GSON](https://github.com/google/gson)
* [Navigation](https://developer.android.com/guide/navigation/navigation-navigate)
* [Glide](https://github.com/bumptech/glide)
* [DataBinding](https://developer.android.com/topic/libraries/data-binding)
* [RoomDB](https://developer.android.com/topic/libraries/architecture/room)
* [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP)
* [FusedLocationProviderClient](https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderClient)


## Overview
This app basically uses GPS to determine the device's location and retrieves the 5 days forecast data accordingly from darksky's API service. 
In order to build this project, you will need to apply for your own API key from [openweathermap](https://openweathermap.org/api). 


## Build the project
 * Obtain your API key from [openweathermap](https://openweathermap.org/api)
 * Add the following attribute to your "gradle.properties" file using this format.
```groovy
API_KEY = "YOUR_API_KEY"
```


## Design
The design inspired by this [example](https://www.uplabs.com/posts/weather-app-7e99c840-f784-42ca-ad24-56192fc04fa7).

## Action!
<img src="https://user-images.githubusercontent.com/52718099/94808574-ffe6fc00-03f9-11eb-8fcb-b1cb87b69e06.gif" width="250" height="500"/>



## License
```license
MIT License

Copyright (c) 2020 Yusuf Sefa Aliş

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
