package nito.club.bff.schema

import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import nito.club.bff.schema.dataloaders.CourseDataLoader
import nito.club.bff.schema.models.Course
import java.util.concurrent.CompletableFuture

class CourseQueryService : Query {
    fun searchCourses(params: CourseSearchParameters, dfe: DataFetchingEnvironment): CompletableFuture<List<Course>> =
        dfe.getDataLoader<Int, Course>(CourseDataLoader.dataLoaderName)
            .loadMany(params.ids)
}

data class CourseSearchParameters(val ids: List<Int>)
