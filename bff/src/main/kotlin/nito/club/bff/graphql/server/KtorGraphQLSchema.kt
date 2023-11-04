package nito.club.bff.graphql.server

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.scalars.IDValueUnboxer
import com.expediagroup.graphql.generator.toSchema
import graphql.GraphQL
import nito.club.bff.graphql.query.HelloQuery

object KtorGraphQLSchema {
    private val config = SchemaGeneratorConfig(
        supportedPackages = listOf("club.nito.graphql"),
    )
    private val queries = listOf(
        TopLevelObject(HelloQuery),
    )
    private val mutations = listOf<TopLevelObject>()

    private val graphQLSchema = toSchema(
        config = config,
        queries = queries,
        mutations = mutations,
    )

    fun getGraphQLObject(): GraphQL = GraphQL.newGraphQL(graphQLSchema)
        .valueUnboxer(IDValueUnboxer())
        .build()
}
