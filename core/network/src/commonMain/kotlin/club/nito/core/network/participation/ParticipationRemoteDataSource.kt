package club.nito.core.network.participation

import club.nito.core.model.participation.ParticipationDeclaration

/**
 * 参加情報を扱うリモートデータソース
 */
sealed interface ParticipationRemoteDataSource {
    /**
     * 該当のスケジュールに参加する
     *
     * @param declaration 参加表明データ
     */
    suspend fun participate(declaration: ParticipationDeclaration): Long
}
