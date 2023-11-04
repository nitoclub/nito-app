package nito.club.bff

import com.expediagroup.graphql.dataloader.KotlinDataLoaderRegistryFactory
import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.graphQLGetRoute
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.ktor.graphQLSDLRoute
import com.expediagroup.graphql.server.ktor.graphQLSubscriptionsRoute
import com.expediagroup.graphql.server.ktor.graphiQLRoute
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.routing.Routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import kotlinx.serialization.json.Json
import nito.club.bff.schema.BookQueryService
import nito.club.bff.schema.CourseQueryService
import nito.club.bff.schema.ExampleSubscriptionService
import nito.club.bff.schema.HelloQueryService
import nito.club.bff.schema.LoginMutationService
import nito.club.bff.schema.UniversityQueryService
import nito.club.bff.schema.dataloaders.BookDataLoader
import nito.club.bff.schema.dataloaders.CourseDataLoader
import nito.club.bff.schema.dataloaders.UniversityDataLoader
import java.time.Duration

@Suppress("unused")
fun Application.graphQLModule() {
    val format = Json {
        ignoreUnknownKeys = true
    }
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(1)

        contentConverter = KotlinxWebsocketSerializationConverter(format)
    }
    install(CORS) {
        anyHost()
    }
    install(GraphQL) {
        schema {
            packages = listOf("nito.club.bff")
            queries = listOf(
                HelloQueryService(),
                BookQueryService(),
                CourseQueryService(),
                UniversityQueryService(),
            )
            mutations = listOf(
                LoginMutationService(),
            )
            subscriptions = listOf(
                ExampleSubscriptionService(),
            )
        }
        engine {
            dataLoaderRegistryFactory = KotlinDataLoaderRegistryFactory(
                UniversityDataLoader,
                CourseDataLoader,
                BookDataLoader,
            )
        }
        server {
            contextFactory = CustomGraphQLContextFactory()
        }
    }
    install(Routing) {
        graphQLGetRoute()
        graphQLPostRoute()
        graphQLSubscriptionsRoute()
        graphiQLRoute()
        graphQLSDLRoute()
    }
}
