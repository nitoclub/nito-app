package club.nito.core.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform