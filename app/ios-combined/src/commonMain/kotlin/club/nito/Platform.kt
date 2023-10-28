package club.nito

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform