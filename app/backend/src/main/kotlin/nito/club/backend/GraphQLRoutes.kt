package nito.club.backend

import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.KtorGraphQLServer
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.serialization
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.application.plugin
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import kotlinx.serialization.StringFormat

fun Route.graphQLGetRoute(
    endpoint: String = "graphql",
    format: StringFormat,
): Route {
    val graphQLPlugin = this.application.plugin(GraphQL)
    return get(endpoint) {
        graphQLPlugin.server.executeRequest(call)
    }.apply {
        install(ContentNegotiation) {
            serialization(ContentType.Application.Json, format)
        }
    }
}

fun Route.graphQLPostRoute(
    endpoint: String = "graphql",
    format: StringFormat,
): Route {
    val graphQLPlugin = this.application.plugin(GraphQL)
    return post(endpoint) {
        graphQLPlugin.server.executeRequest(call)
    }.apply {
        install(ContentNegotiation) {
            serialization(ContentType.Application.Json, format)
        }
    }
}

private suspend inline fun KtorGraphQLServer.executeRequest(call: ApplicationCall) = try {
    execute(call.request)?.let {
        call.respond(it)
    } ?: call.respond(HttpStatusCode.BadRequest)
} catch (e: UnsupportedOperationException) {
    call.respond(HttpStatusCode.MethodNotAllowed)
} catch (e: Exception) {
    call.respond(HttpStatusCode.BadRequest)
}
