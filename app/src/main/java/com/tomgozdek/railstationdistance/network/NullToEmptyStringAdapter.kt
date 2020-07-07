package com.tomgozdek.railstationdistance.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
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
    @NullToEmptyString
    fun fromJson(reader: JsonReader): String? {
        val result = if (reader.peek() === JsonReader.Token.NULL) {
            reader.nextNull()
        } else {
            reader.nextString()
        }

        return result ?: ""
    }
}

