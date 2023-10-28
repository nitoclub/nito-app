package club.nito.feature.settings

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform