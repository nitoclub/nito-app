package club.nito.core.common

import platform.Foundation.NSUUID

actual fun randomUUIDHash(): Int = NSUUID().hash.toInt()
