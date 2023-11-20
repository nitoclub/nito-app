package nito.club.backend.schema

import com.expediagroup.graphql.server.operations.Mutation
import nito.club.backend.schema.models.User

data class AuthPayload(val token: String? = null, val user: User? = null)

class LoginMutationService : Mutation {
    suspend fun login(email: String, password: String, aliasUUID: String?): AuthPayload {
        val token = "fake-token"
        val user = User(
            email = "fake@site.com",
            firstName = "Someone",
            lastName = "You Don't know",
            universityId = 4,
        )
        return AuthPayload(token, user)
    }
}
