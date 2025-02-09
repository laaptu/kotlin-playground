package org.ahivs.kotlin.serialization

import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.IntArraySerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.ahivs.kotlin.serialization.CustomSerialization.serializeTest
import java.text.SimpleDateFormat
import java.util.*

object CustomSerialization {

    @Serializable
    data class DateInfo(
        @Contextual
        val timeZone: TimeZone,
        @Contextual
        val calendar: Calendar,
    )

    val customSerializersModule = SerializersModule {
        contextual(CalendarSerializer2)
        contextual(TimeZoneSerializer)
    }

    val json = Json {
        serializersModule = customSerializersModule
        prettyPrint = true
    }

    object TimeZoneSerializer : KSerializer<TimeZone> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("TimeZone", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: TimeZone) {
            // Serialize the TimeZone as an ID string
            encoder.encodeString(value.id)
        }

        override fun deserialize(decoder: Decoder): TimeZone {
            // Deserialize back to a TimeZone object from the ID string
            val id = decoder.decodeString()
            return TimeZone.getTimeZone(id)
        }
    }

    object CalendarSerializer : KSerializer<Calendar> {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").apply {
            timeZone = TimeZone.getTimeZone("UTC")  // Ensure UTC is used during formatting
        }

        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("Calendar", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: Calendar) {
            // Serialize Calendar to ISO 8601 formatted string
            val formatted = dateFormat.format(value.time)
            encoder.encodeString(formatted)
        }

        override fun deserialize(decoder: Decoder): Calendar {
            // Deserialize back to Calendar from ISO 8601 string
            val dateString = decoder.decodeString()
            return Calendar.getInstance().apply {
                time = dateFormat.parse(dateString)!!
            }
        }
    }

    object CalendarSerializer1 : KSerializer<Calendar> {

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Calendar") {
            element<Long>("timeInMillis")
            element<String>("timeZoneId")
        }

        override fun serialize(encoder: Encoder, value: Calendar) {
            // Start serializing a structured object
            encoder.encodeStructure(descriptor) {
                encodeLongElement(descriptor, 0, value.timeInMillis)
                encodeStringElement(descriptor, 1, value.timeZone.id)
            }
        }

        override fun deserialize(decoder: Decoder): Calendar {
            return decoder.decodeStructure(descriptor) {

                // Directly decode the elements at known positions
                val timeInMillis = decodeElement(descriptor) {
                    decodeLongElement(descriptor, 0)
                }
                val timeZoneId = decodeElement(descriptor) {
                    decodeStringElement(descriptor, 1)
                }

                Calendar.getInstance(TimeZone.getTimeZone(timeZoneId)).apply {
                    this.timeInMillis = timeInMillis
                }
            }
        }
    }

    object CalendarSerializer2 : KSerializer<Calendar> {

        val serializer = ListSerializer(String.serializer())
        override val descriptor: SerialDescriptor =  serializer.descriptor

        override fun serialize(encoder: Encoder, value: Calendar) {
            // Create a list with timeInMillis as a String and timeZone.id
            val serializedList = listOf(
                value.timeInMillis.toString(), // Convert Long to String
                value.timeZone.id // Get the timeZone ID as String
            )
            // Encode the list
            encoder.encodeSerializableValue(serializer, serializedList)
        }

        override fun deserialize(decoder: Decoder): Calendar {
            // Decode the list of Strings
            val list = decoder.decodeSerializableValue(serializer)

            // Convert the first element back to Long and second to TimeZone
            val timeInMillis = list[0].toLong() // Convert String to Long
            val timeZoneId = list[1] // Get the timeZone ID as String

            // Create a Calendar instance
            return Calendar.getInstance(TimeZone.getTimeZone(timeZoneId)).apply {
                this.timeInMillis = timeInMillis // Set the timeInMillis
            }
        }
    }

    fun serializeTest() {
        val dateInfo = DateInfo(
            timeZone = TimeZone.getTimeZone("GMT+10:00"),
            calendar = Calendar.getInstance()
        )
        val jsonString = json.encodeToString(dateInfo)
        val dateDecoded = json.decodeFromString<DateInfo>(jsonString)
        //println(json.encodeToString(DateInfo.serializer(), dateInfo))
        println(jsonString)
        println(dateDecoded)
    }

    inline fun <T> CompositeDecoder.decodeElement(
        descriptor: SerialDescriptor,
        crossinline block: CompositeDecoder.() -> T
    ): T {
        decodeElementIndex(descriptor)
        return block()
    }



}

fun main() {
    serializeTest()
}