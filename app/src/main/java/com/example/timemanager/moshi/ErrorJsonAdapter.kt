package com.example.timemanager.moshi

import com.example.timemanager.data.DataError
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter


/**
 * Created by Alexander Shibaev on 11.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class ErrorJsonAdapter: JsonAdapter<DataError>() {
    override fun fromJson(reader: JsonReader): DataError {
        reader.beginObject()
        var errorMessage: String? = null
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "error" -> errorMessage = reader.nextString()
                else -> reader.skipValue()
            }
        }
        reader.endObject()
        return DataError(errorMessage)
    }

    override fun toJson(writer: JsonWriter, value: DataError?) {
        writer.beginObject()
        writer.name("error").value(value?.error)
        writer.endObject()
    }
}