package club.nito.core.common

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform