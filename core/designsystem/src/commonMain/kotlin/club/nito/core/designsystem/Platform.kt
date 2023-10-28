package club.nito.core.designsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform