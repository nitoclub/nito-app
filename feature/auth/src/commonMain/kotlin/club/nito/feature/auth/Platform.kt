package club.nito.feature.auth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform