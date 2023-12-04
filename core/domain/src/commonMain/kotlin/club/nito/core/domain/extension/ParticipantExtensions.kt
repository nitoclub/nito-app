package club.nito.core.domain.extension

import club.nito.core.model.participant.Participant

/**
 * 重複を除いたユーザー ID のリストに変換する
 */
internal fun List<Participant>.toDistinctUserIdList(): List<String> = distinctBy { it.userId }.toUserIdList()

/**
 * ユーザー ID のリストに変換する
 */
internal fun List<Participant>.toUserIdList(): List<String> = map { it.userId }
