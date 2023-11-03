package club.nito.core.data

import club.nito.core.model.participation.ParticipationDeclaration

/**
 * 参加情報を扱うリポジトリ
 */
sealed interface ParticipationRepository {
    /**
     * 該当のスケジュールに参加する
     *
     * @param declaration 参加表明データ
     */
    suspend fun participate(declaration: ParticipationDeclaration): Long
}
