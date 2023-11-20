package nito.club.backend.graphql.query

import com.expediagroup.graphql.server.operations.Query

object HelloQuery : Query {
    fun hello() = "Hello World!"
}
