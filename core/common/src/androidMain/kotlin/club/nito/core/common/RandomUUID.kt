package club.nito.core.common

import java.util.UUID

actual fun randomUUIDHash(): Int = UUID.randomUUID().hashCode()
