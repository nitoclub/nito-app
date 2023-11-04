package nito.club.bff.graphql.server

import com.expediagroup.graphql.server.execution.GraphQLRequestHandler
import com.expediagroup.graphql.server.execution.GraphQLServer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.ApplicationRequest
import io.ktor.server.response.respond

object KtorGraphQLServer {
    private val mapper = jacksonObjectMapper()
    private val ktorGraphQLServer = getGraphQLServer()

    suspend fun handle(applicationCall: ApplicationCall) {
        val result = ktorGraphQLServer.execute(applicationCall.request)

        if (result != null) {
            val json = mapper.writeValueAsString(result)
            applicationCall.response.call.respond(json)
        } else {
            applicationCall.response.call.respond(HttpStatusCode.BadRequest, "Invalid request")
        }
    }

    private fun getGraphQLServer(): GraphQLServer<ApplicationRequest> {
        val requestParser = KtorGraphQLRequestParser(mapper)
        val contextFactory = KtorGraphQLContextFactory()
        val graphQL = KtorGraphQLSchema.getGraphQLObject()
        val requestHandler = GraphQLRequestHandler(graphQL)

        return GraphQLServer(requestParser, contextFactory, requestHandler)
    }
}
