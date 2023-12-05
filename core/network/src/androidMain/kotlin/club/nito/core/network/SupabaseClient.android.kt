package club.nito.core.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor

internal actual fun createHttpEngine(): HttpClientEngine? = OkHttp.create {
    config {
        followRedirects(true)
        followSslRedirects(true)
        retryOnConnectionFailure(true)

        addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (true) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            },
        )
    }
}
