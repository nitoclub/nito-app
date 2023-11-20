package nito.club.backend.schema

import com.expediagroup.graphql.server.operations.Query

class HelloQueryService : Query {
    fun hello() = "World!"
}
