package club.nito.core.common

import java.util.UUID

public actual fun randomUUIDHash(): Int = UUID.randomUUID().hashCode()
