package hu.griddy.kotlinbasics.model

data class ColorResponse(
    val data:List<Color>
)

data class Color(
    val name:String,
    val year:Int,
    val color:String,
    val pantone_value: String
)