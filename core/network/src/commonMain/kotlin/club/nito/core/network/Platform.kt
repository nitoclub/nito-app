package club.nito.core.network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform