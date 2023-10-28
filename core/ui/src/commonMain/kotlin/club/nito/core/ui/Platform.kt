package club.nito.core.ui

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform