package club.nito.feature.top

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform