package hu.griddy.kotlinbasics.model

data class WeatherResponse(
    var main:Main,
    var weather:List<Weather>
)
data class Main(
    var temp:Double
)
data class Weather(
    var description:String
)