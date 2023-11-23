package club.nito.core.common

import platform.Foundation.NSUUID

public actual fun randomUUIDHash(): Int = NSUUID().hash.toInt()
