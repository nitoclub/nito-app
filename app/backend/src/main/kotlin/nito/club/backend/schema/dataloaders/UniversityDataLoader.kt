package nito.club.backend.schema.dataloaders

import com.expediagroup.graphql.dataloader.KotlinDataLoader
import graphql.GraphQLContext
import kotlinx.coroutines.runBlocking
import nito.club.backend.schema.models.University
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

val UniversityDataLoader = object : KotlinDataLoader<Int, University?> {
    override val dataLoaderName = "UNIVERSITY_LOADER"
    override fun getDataLoader(graphQLContext: GraphQLContext) =
        DataLoaderFactory.newDataLoader { ids ->
            CompletableFuture.supplyAsync {
                runBlocking { University.search(ids).toMutableList() }
            }
        }
}
