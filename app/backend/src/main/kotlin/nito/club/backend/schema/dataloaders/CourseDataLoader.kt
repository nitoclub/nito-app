package nito.club.backend.schema.dataloaders

import com.expediagroup.graphql.dataloader.KotlinDataLoader
import graphql.GraphQLContext
import kotlinx.coroutines.runBlocking
import nito.club.backend.schema.models.Course
import org.dataloader.DataLoaderFactory
import java.util.concurrent.CompletableFuture

val CourseDataLoader = object : KotlinDataLoader<Int, Course?> {
    override val dataLoaderName = "COURSE_LOADER"
    override fun getDataLoader(graphQLContext: GraphQLContext) =
        DataLoaderFactory.newDataLoader { ids ->
            CompletableFuture.supplyAsync {
                runBlocking { Course.search(ids).toMutableList() }
            }
        }
}
