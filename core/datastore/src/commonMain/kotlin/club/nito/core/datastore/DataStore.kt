package club.nito.core.datastore

public sealed interface DataStore {
    public suspend fun setRefreshed(isRefreshed: Boolean)
    public suspend fun isRefreshed(): Boolean
}
