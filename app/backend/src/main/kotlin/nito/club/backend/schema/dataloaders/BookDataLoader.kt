package nito.club.backend.schema.dataloaders

import com.expediagroup.graphql.dataloader.KotlinDataLoader
import graphql.GraphQLContext
import kotlinx.coroutines.runBlocking
import nito.club.backend.schema.models.Book
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

val BookDataLoader = object : KotlinDataLoader<Int, Book?> {
    override val dataLoaderName = "BOOK_LOADER"
    override fun getDataLoader(graphQLContext: GraphQLContext) =
        DataLoaderFactory.newDataLoader { ids ->
            CompletableFuture.supplyAsync {
                runBlocking { Book.search(ids).toMutableList() }
            }
        }
}
