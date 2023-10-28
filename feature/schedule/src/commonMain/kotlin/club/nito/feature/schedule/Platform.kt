package club.nito.feature.schedule

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform