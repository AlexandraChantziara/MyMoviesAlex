package com.texnognosia.mymoviesalex.database

import androidx.room.TypeConverter
import com.texnognosia.mymoviesalex.dataclasses.Ratings
import com.texnognosia.mymoviesalex.mapToObject
import com.texnognosia.mymoviesalex.mapToString
import java.math.BigDecimal

class TypeAdapters {
    @TypeConverter
    fun bigDecimalToString(bigDecimal: BigDecimal?) = bigDecimal?.toString()

    @TypeConverter
    fun stringToBigDecimal(string: String?) = string?.toBigDecimal()

    @TypeConverter
    fun listLongToString(list: List<Long>?) : String? = list?.joinToString(",")

    @TypeConverter
    fun stringToListLong(string: String?) : List<Long>? = string?.split(",")?.mapNotNull { it.toLongOrNull() }

    @TypeConverter
    fun listToString(list: List<Ratings>): String = mapToString(list)

    @TypeConverter
    fun stringToList(string: String): List<Ratings> = mapToObject<List<Ratings>>(string) ?: emptyList()
}
