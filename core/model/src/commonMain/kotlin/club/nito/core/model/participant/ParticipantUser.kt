package club.nito.core.model.participant

import club.nito.core.model.UserProfile

/**
 * 参加ユーザー
 * @param profile ユーザープロフィール
 * @param status 参加状況
 */
public data class ParticipantUser(
    val profile: UserProfile,
    val status: ParticipantStatus,
)

/**
 * 参加のみでフィルタリングする
 */
public fun Collection<ParticipantUser>.filterIsAttendance(): List<ParticipantUser> {
    return filter { it.status == ParticipantStatus.ATTENDANCE }
}

/**
 * 欠席のみでフィルタリングする
 */
public fun Collection<ParticipantUser>.filterIsAbsence(): List<ParticipantUser> {
    return filter { it.status == ParticipantStatus.ABSENCE }
}

/**
 * 未定のみでフィルタリングする
 */
public fun Collection<ParticipantUser>.filterIsPending(): List<ParticipantUser> {
    return filter { it.status == ParticipantStatus.PENDING }
}

/**
 * ユーザープロフィールのリストに変換する
 */
public fun Collection<ParticipantUser>.toUserProfileList(): List<UserProfile> = map { it.profile }
