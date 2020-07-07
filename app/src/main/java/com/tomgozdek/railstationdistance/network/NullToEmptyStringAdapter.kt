package com.tomgozdek.railstationdistance.network

import androidx.annotation.Nullable
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

/**
 * Credits to https://github.com/square/moshi/issues/522
 */

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class NullToEmptyString

class NullToEmptyStringAdapter  {
    @ToJson
    fun toJson(@NullToEmptyString value: String?) : String? {
        return value
    }

    @FromJson
    fun fromJson(@Nullable data: String?) : String? {
        return data ?: ""
    }
}

