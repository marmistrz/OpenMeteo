package com.marmistrz.openmeteo.providers

sealed class WeatherForecast {
    abstract fun showForecast()
}

class MeteogramForecast(val url: String) : WeatherForecast() {
    override fun showForecast() {
        throw NotImplementedError()
    }
}

interface WeatherProvider {
    fun retrieveWeather(): WeatherForecast
}
