package club.nito.core.model

/**
 * 実行結果
 */
sealed interface ExecuteResult<out T> {
    /**
     * 成功
     */
    data class Success<T>(val data: T) : ExecuteResult<T>

    /**
     * 失敗
     */
    data class Failure(val error: NitoError?) : ExecuteResult<Nothing>
}
