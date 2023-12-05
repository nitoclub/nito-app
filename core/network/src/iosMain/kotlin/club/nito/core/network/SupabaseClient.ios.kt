package club.nito.core.network

import club.nito.core.model.BuildConfig
import io.ktor.client.engine.HttpClientEngine

internal actual fun createHttpEngine(buildConfig: BuildConfig): HttpClientEngine? = null
