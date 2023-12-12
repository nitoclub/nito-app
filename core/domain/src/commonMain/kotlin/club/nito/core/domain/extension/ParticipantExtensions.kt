package club.nito.core.domain.extension

import club.nito.core.model.participant.ParticipantUser
import club.nito.core.model.UserProfile
import club.nito.core.model.participant.Participant

/**
 * 重複を除いたユーザー ID のリストに変換する
 */
internal fun List<Participant>.toDistinctUserIdList(): List<String> = distinctBy { it.userId }.toUserIdList()

/**
 * ユーザー ID のリストに変換する
 */
internal fun List<Participant>.toUserIdList(): List<String> = map { it.userId }

/**
 * 参加ユーザー のリストに変換する
 */
internal fun Collection<UserProfile>.toParticipantUserList(participants: List<Participant>): List<ParticipantUser> =
    asSequence()
        .filter { profile -> participants.any { it.userId == profile.id } }
        .map { profile ->
            ParticipantUser(
                profile = profile,
                status = participants.first { it.userId == profile.id }.status,
            )
        }
        .toList()
