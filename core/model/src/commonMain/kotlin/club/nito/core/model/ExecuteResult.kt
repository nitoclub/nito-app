package club.nito.core.model

/**
 * 実行結果
 */
public sealed interface ExecuteResult<out T> {
    /**
     * 成功
     */
    public data class Success<T>(val data: T) : ExecuteResult<T>

    /**
     * 失敗
     */
    public data class Failure(val error: NitoError?) : ExecuteResult<Nothing>
}

public suspend fun <T> runExecuting(block: suspend () -> T): ExecuteResult<T> = try {
    ExecuteResult.Success(block())
} catch (e: Throwable) {
    ExecuteResult.Failure(e.toNitoError())
}

public suspend inline fun <T> ExecuteResult<T>.handleResult(
    noinline onSuccess: suspend (T) -> Unit = {},
    noinline onFailure: suspend (NitoError?) -> Unit = {},
) {
    when (this) {
        is ExecuteResult.Success -> onSuccess(data)
        is ExecuteResult.Failure -> onFailure(error)
    }
}
