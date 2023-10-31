package club.nito.core.model

/**
 * 単一のデータを取得する結果
 */
sealed interface FetchSingleResult<out T> {
    /**
     * 取得中
     */
    data object Loading : FetchSingleResult<Nothing>

    /**
     * 取得成功
     */
    data class Success<T>(val data: T) : FetchSingleResult<T>

    /**
     * 取得失敗
     */
    data class Failure(val error: NitoError?) : FetchSingleResult<Nothing>
}
