package club.nito.core.model

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform