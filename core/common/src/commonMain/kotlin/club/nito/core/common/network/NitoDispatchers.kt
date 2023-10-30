package club.nito.core.common.network

import kotlinx.coroutines.CoroutineDispatcher

enum class NitoDispatchers {
    Default,
    IO,
}

internal expect val defaultDispatcher: CoroutineDispatcher

internal expect val ioDispatcher: CoroutineDispatcher
