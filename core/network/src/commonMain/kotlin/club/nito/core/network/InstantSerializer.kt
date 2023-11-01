package club.nito.core.network

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        "Date",
        PrimitiveKind.LONG,
    )

    override fun serialize(encoder: Encoder, value: Instant) = throw NotImplementedError()
    override fun deserialize(decoder: Decoder): Instant = Instant.parse(decoder.decodeString())
}
