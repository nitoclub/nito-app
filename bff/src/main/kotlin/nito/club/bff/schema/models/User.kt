package nito.club.bff.schema.models

import graphql.GraphQLException

data class User(
    val email: String,
    val firstName: String?,
    val lastName: String?,
    val universityId: Int?,
    val isAdmin: Boolean = false,
) {
    suspend fun university(): University? {
        universityId ?: return null
        return University.search(listOf(universityId))[0]
    }

    fun intThatNeverComes(): Int =
        throw GraphQLException("This value will never return")
}
