package com.example.sixtv.network

data class BaseListData<T>(
    val data: List<T> = emptyList(),
    val code: Int = -1,
    val msg: String = "默认"
)

data class Tv(
    val id: String = "",
    val name: String = "",
    val type: String = "",
) {
    fun getTypeFormat(): TvType? {
        return when (type) {
            Beverage.value -> Beverage
            Food.value -> Food
            else -> null
        }
    }
}

sealed class TvType(val value: String)
object Beverage : TvType("Beverage")
object Food : TvType("Food")

typealias TvListRepo = BaseListData<Tv>