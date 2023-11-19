package club.nito.core.network

import club.nito.core.model.Order
import io.github.jan.supabase.postgrest.query.Order as SupabaseOrder

/**
 * Supabaseのソート順に変換する
 */
internal fun Order.toSupabaseOrder(): SupabaseOrder {
    return when (this) {
        Order.ASCENDING -> SupabaseOrder.ASCENDING
        Order.DESCENDING -> SupabaseOrder.DESCENDING
    }
}
