package nito.club.backend.schema

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import nito.club.backend.schema.dataloaders.BookDataLoader
import nito.club.backend.schema.models.Book
import java.util.concurrent.CompletableFuture

class BookQueryService : Query {
    @GraphQLDescription("Return list of books based on BookSearchParameter options")
    @Suppress("unused")
    fun searchBooks(params: BookSearchParameters, dfe: DataFetchingEnvironment): CompletableFuture<List<Book>> =
        dfe.getDataLoader<Int, Book>(BookDataLoader.dataLoaderName)
            .loadMany(params.ids)
}

data class BookSearchParameters(val ids: List<Int>)
