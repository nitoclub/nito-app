package club.nito.core.database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform