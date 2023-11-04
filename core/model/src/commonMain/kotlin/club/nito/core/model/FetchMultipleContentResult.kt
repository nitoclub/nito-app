package club.nito.core.model

/**
 * 複数のコンテンツデータを取得する結果
 */
sealed interface FetchMultipleContentResult<out T> {
    /**
     * 取得中
     */
    data object Loading : FetchMultipleContentResult<Nothing>

    /**
     * 該当データなし
     */
    data object NoContent : FetchMultipleContentResult<Nothing>

    /**
     * 取得成功
     */
    data class Success<T>(val data: List<T>) : FetchMultipleContentResult<T>

    /**
     * 取得失敗
     */
    data class Failure(val error: NitoError?) : FetchMultipleContentResult<Nothing>
}
