package com.toptal.data.network.di

import com.apollographql.apollo3.ApolloClient
import com.toptal.data.network.model.repository_details.RepositoryDetailsImpl
import com.toptal.data.network.model.repository_details.RepositoryDetailsMapper
import com.toptal.data.network.model.repository_node.RepositoryNodeClientImpl
import com.toptal.data.network.model.repository_node.RepositoryNodeMapper
import com.toptal.domain.usecases.repository_details.RepositoryDetailsClient
import com.toptal.domain.usecases.repository_nodes.RepositoryNodeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) // no need for repository and usecases to live longer than view models in our cases
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideRepositoryNodeClient(
        apolloClient: ApolloClient,
        mapper: RepositoryNodeMapper
    ): RepositoryNodeClient {
        return RepositoryNodeClientImpl(
            apolloClient,
            mapper
        )
    }

    @Provides
    @ViewModelScoped
    fun provideRepositoryDetailsClient(
        apolloClient: ApolloClient,
        mapper: RepositoryDetailsMapper
    ): RepositoryDetailsClient {
        return RepositoryDetailsImpl(
            apolloClient,
            mapper
        )
    }
}