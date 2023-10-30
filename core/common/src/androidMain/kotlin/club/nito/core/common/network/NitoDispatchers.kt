package club.nito.core.common.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val nitaDispatcher: NitoDispatchers)

internal actual val defaultDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default

internal actual val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO
