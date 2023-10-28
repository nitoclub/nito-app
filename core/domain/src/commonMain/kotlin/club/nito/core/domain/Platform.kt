package club.nito.core.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform