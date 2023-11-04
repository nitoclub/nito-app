package nito.club.bff.graphql.server

import com.expediagroup.graphql.server.execution.GraphQLContextFactory
import io.ktor.server.request.ApplicationRequest

class KtorGraphQLContextFactory : GraphQLContextFactory<ApplicationRequest>
