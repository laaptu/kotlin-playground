package org.ahivs.kotlin.serialization.custom

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.ahivs.kotlin.serialization.TimeBounds
import org.ahivs.kotlin.serialization.custom.JsonSerializer.customSerializersModule
import org.ahivs.kotlin.serialization.custom.JsonSerializer.jsonSerializer
import java.util.Calendar
import java.util.TimeZone

object JsonSerializer {

    val customSerializersModule = SerializersModule {
        contextual(TimeZoneSerializer)
        contextual(CalendarSerializer)
        contextual(TimeBounds::class, TimeBoundsSerializer)
    }
    val jsonSerializer = Json {
        serializersModule = customSerializersModule
        prettyPrint = true
    }

    val secondSerializerModule = SerializersModule {
        customSerializersModule
    }


    fun getJsonSerializer(customSerializersModule: SerializersModule = JsonSerializer.customSerializersModule) = Json {
        serializersModule = customSerializersModule
        prettyPrint = true
    }


    private object TimeZoneSerializer : KSerializer<TimeZone> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TimeZone", STRING)
        override fun serialize(encoder: Encoder, value: TimeZone) = encoder.encodeString(value.id)
        override fun deserialize(decoder: Decoder): TimeZone = TimeZone.getTimeZone(decoder.decodeString())
    }

    private object TimeBoundsSerializer : KSerializer<TimeBounds> {
        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("TimeBound") {
            element("start", CalendarSerializer.descriptor)
            element("end", CalendarSerializer.descriptor)
        }

        override fun deserialize(decoder: Decoder): TimeBounds {
            return decoder.decodeStructure(descriptor) {
                val start = decodeElement(descriptor) {
                    decodeSerializableElement(descriptor, 0, CalendarSerializer)
                }
                val end = decodeElement(descriptor) {
                    decodeSerializableElement(descriptor, 1, CalendarSerializer)
                }
                TimeBounds(start, end)
            }
        }

        override fun serialize(encoder: Encoder, value: TimeBounds) {
            encoder.encodeStructure(descriptor) {
                encodeSerializableElement(descriptor, 0, CalendarSerializer, value.start)
                encodeSerializableElement(descriptor, 1, CalendarSerializer, value.end)
            }
        }

    }

    object CalendarSerializer : KSerializer<Calendar> {

        @OptIn(ExperimentalSerializationApi::class)
        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Calendar") {
            element<Long>("timeInMillis")
            element(TimeZoneSerializer.descriptor.serialName, TimeZoneSerializer.descriptor)

        }

        override fun serialize(encoder: Encoder, value: Calendar) {
            encoder.encodeStructure(descriptor) {
                encodeLongElement(descriptor, 0, value.timeInMillis)
                encodeSerializableElement(descriptor, 1, TimeZoneSerializer, value.timeZone)
            }
        }

        override fun deserialize(decoder: Decoder): Calendar {
            return decoder.decodeStructure(descriptor) {
                val timeInMillis = decodeElement(descriptor) {
                    decodeLongElement(descriptor, 0)
                }
                val timeZone = decodeElement(descriptor) {
                    decodeSerializableElement(descriptor, 1, TimeZoneSerializer)
                }
                Calendar.getInstance(timeZone).apply {
                    this.timeInMillis = timeInMillis
                }
            }
        }

    }

    private inline fun <T> CompositeDecoder.decodeElement(
        descriptor: SerialDescriptor,
        crossinline block: CompositeDecoder.() -> T
    ): T {
        decodeElementIndex(descriptor)
        return block()
    }
}

@Serializable
data class TimeInfo(
    @Contextual
    val timeZone: TimeZone,
    @Contextual
    val calendar: Calendar
)


fun main() {
    val timeZone = TimeZone.getTimeZone("Europe/Berlin")
    val calendar = Calendar.getInstance(timeZone).apply {
        set(2024, 10, 10)
    }
    println(calendar.time)
    val timeInfo = TimeInfo(timeZone, calendar)
    val json = jsonSerializer.encodeToString(timeInfo)
    println(json)
    val jsonTimeInfo = jsonSerializer.decodeFromString<TimeInfo>(json)
    println(jsonTimeInfo.calendar.time)
    println(jsonTimeInfo)

}