package club.nito.core.common.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal actual val defaultDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default

internal actual val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO
