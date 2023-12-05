package club.nito.core.network

import club.nito.core.model.BuildConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor

internal actual fun createHttpEngine(buildConfig: BuildConfig): HttpClientEngine? = OkHttp.create {
    config {
        followRedirects(true)
        followSslRedirects(true)
        retryOnConnectionFailure(true)

        addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (buildConfig.debugBuild) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            },
        )
    }
}
