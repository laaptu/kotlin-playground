package org.ahivs.kotlin.serialization

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import org.ahivs.kotlin.serialization.custom.JsonSerializer.jsonSerializer
import java.util.*

class TimeBounds(start: Calendar = Calendar.getInstance(), end: Calendar = Calendar.getInstance()) {
    val timeZone = start.timeZone
    var start: Calendar
        private set
    var end: Calendar
        private set

    init {
        val providedEndTime = if (end.timeZone != timeZone) end changeTimeZoneTo timeZone else end
        val startEnd = getStartEndTime(start, providedEndTime)
        this.start = startEnd.first
        this.end = startEnd.second
    }

    private fun getStartEndTime(start: Calendar, end: Calendar): Pair<Calendar, Calendar> {
        return when (start.after(end)) {
            true -> Pair(end, start)
            else -> Pair(start, end)
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is TimeBounds) {
            start == other.start && end == other.end
        } else {
            false
        }
    }

    @Suppress("MagicNumber")
    override fun hashCode(): Int {
        var result = start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }
}

infix fun Calendar.changeTimeZoneTo(timeZone: TimeZone): Calendar {
    val currTimeInMillis = timeInMillis
    return Calendar.getInstance(timeZone).apply {
        this.timeInMillis = currTimeInMillis
    }
}

fun main() {
    val start = Calendar.getInstance().apply {
        set(2024, 10, 10)
    }
    val end = Calendar.getInstance().apply {
        set(2024, 10, 11)
    }
    val timeBounds = TimeBounds(start, end)
    val json = jsonSerializer.encodeToString(timeBounds)
    println(json)

    val decodedTimeBounds = jsonSerializer.decodeFromString<TimeBounds>(json)
    println(decodedTimeBounds)
}