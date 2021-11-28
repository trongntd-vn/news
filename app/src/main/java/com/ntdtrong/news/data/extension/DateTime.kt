package com.ntdtrong.news.data.extension

import org.threeten.bp.Instant
import java.text.DateFormat
import java.util.*

val String.displayDateTime: String
    get() {
        return try {
            val df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT)
            val time = Instant.parse(this).epochSecond
            val date = Date(time * 1000)
            df.format(date)
        } catch (e: Exception) {
            ""
        }
    }