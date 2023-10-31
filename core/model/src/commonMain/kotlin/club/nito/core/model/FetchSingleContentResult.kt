package club.nito.core.model

/**
 * 単一のコンテンツデータを取得する結果
 */
sealed interface FetchSingleContentResult<out T> {
    /**
     * 取得中
     */
    data object Loading : FetchSingleContentResult<Nothing>

    /**
     * 該当データなし
     */
    data object NoContent : FetchSingleContentResult<Nothing>

    /**
     * 取得成功
     */
    data class Success<T>(val data: T) : FetchSingleContentResult<T>

    /**
     * 取得失敗
     */
    data class Failure(val error: NitoError?) : FetchSingleContentResult<Nothing>
}
