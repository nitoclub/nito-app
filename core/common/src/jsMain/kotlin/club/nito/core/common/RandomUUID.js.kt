package club.nito.core.common

import kotlin.random.Random

// Number of bytes in a UUID
internal const val UUID_BYTES = 16

public actual fun randomUUIDHash(): Int = Random.Default.nextBytes(UUID_BYTES).hashCode()
