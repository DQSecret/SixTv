package com.example.sixtv.network

import android.graphics.Color

data class BaseData<T>(
    val data: T? = null,
    val code: Int = -1,
    val msg: String = "默认"
)

data class BaseListData<T>(
    val data: List<T> = emptyList(),
    val code: Int = -1,
    val msg: String = "默认"
)

data class Tv(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val tvName: String = "",
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

data class Menu(
    val no: String = "",
    val name: String = "",
    val enName: String = "",
    val abv: String = "",
    val single: String = "",
    val l1: String = "",
    val l2: String = "",
    val cans: String = "",
    val isNew: String = "",
    val winning: String = "",
    val shortContent: String = "",
) {

    fun isBlank() = name.isBlank()

    fun getBgColor(): Int? {
        return no.toIntOrNull()?.let {
            if (it % 2 == 1) {
                Color.parseColor("#1A77BD")
            } else {
                Color.parseColor("#10212A")
            }
        }
    }
}

data class MenuEntity(
    val tv: Tv,
    val menu: List<Menu> = emptyList()
) {
    companion object {
        fun getSimple() = MenuEntity(Tv(), emptyList())
    }
}

typealias MenuRepo = BaseData<MenuEntity>