package nito.club.bff.schema

import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import nito.club.bff.schema.dataloaders.UniversityDataLoader
import nito.club.bff.schema.models.University
import java.util.concurrent.CompletableFuture

class UniversityQueryService : Query {
    fun searchUniversities(
        params: UniversitySearchParameters,
        dfe: DataFetchingEnvironment,
    ): CompletableFuture<List<University>> =
        dfe.getDataLoader<Int, University>(UniversityDataLoader.dataLoaderName)
            .loadMany(params.ids)
}

data class UniversitySearchParameters(val ids: List<Int>)
