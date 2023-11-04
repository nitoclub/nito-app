package club.nito.core.data

import club.nito.core.model.participation.ParticipantDeclaration

/**
 * 参加情報を扱うリポジトリ
 */
sealed interface ParticipantRepository {
    /**
     * 該当のスケジュールに参加する
     *
     * @param declaration 参加表明データ
     */
    suspend fun participate(declaration: ParticipantDeclaration): Long
}
