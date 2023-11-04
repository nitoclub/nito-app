package nito.club.bff

import com.expediagroup.graphql.generator.extensions.plus
import com.expediagroup.graphql.server.ktor.DefaultKtorGraphQLContextFactory
import graphql.GraphQLContext
import io.ktor.server.request.ApplicationRequest
import nito.club.bff.schema.models.User

class CustomGraphQLContextFactory : DefaultKtorGraphQLContextFactory() {
    override suspend fun generateContext(request: ApplicationRequest): GraphQLContext =
        super.generateContext(request).plus(
            mutableMapOf<Any, Any>(
                "user" to User(
                    email = "fake@site.com",
                    firstName = "Someone",
                    lastName = "You Don't know",
                    universityId = 4,
                ),
            ).also { map ->
                request.headers["my-custom-header"]?.let { customHeader ->
                    map["customHeader"] = customHeader
                }
            },
        )
}
