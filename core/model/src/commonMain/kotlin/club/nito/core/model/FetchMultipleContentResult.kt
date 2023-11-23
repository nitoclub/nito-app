package club.nito.core.model

/**
 * 複数のコンテンツデータを取得する結果
 */
public sealed interface FetchMultipleContentResult<out T> {
    /**
     * 取得中
     */
    public data object Loading : FetchMultipleContentResult<Nothing>

    /**
     * 該当データなし
     */
    public data object NoContent : FetchMultipleContentResult<Nothing>

    /**
     * 取得成功
     */
    public data class Success<T>(val data: List<T>) : FetchMultipleContentResult<T>

    /**
     * 取得失敗
     */
    public data class Failure(val error: NitoError?) : FetchMultipleContentResult<Nothing>
}
