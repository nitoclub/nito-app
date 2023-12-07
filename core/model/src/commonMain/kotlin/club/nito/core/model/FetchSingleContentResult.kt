package club.nito.core.model

/**
 * 単一のコンテンツデータを取得する結果
 */
public sealed interface FetchSingleContentResult<out T> {
    /**
     * 取得中
     */
    public data object Loading : FetchSingleContentResult<Nothing>

    /**
     * 該当データなし
     */
    public data object NoContent : FetchSingleContentResult<Nothing>

    /**
     * 取得成功
     */
    public data class Success<T>(val data: T) : FetchSingleContentResult<T>

    /**
     * 取得失敗
     */
    public data class Failure(val error: NitoError?) : FetchSingleContentResult<Nothing>
}

public suspend inline fun <T> runFetchSingleContent(crossinline block: suspend () -> T): FetchSingleContentResult<T> =
    try {
        FetchSingleContentResult.Success(block())
    } catch (e: Throwable) {
        FetchSingleContentResult.Failure(e.toNitoError())
    }
