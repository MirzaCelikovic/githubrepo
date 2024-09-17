package com.toptal.data.network.di

import com.apollographql.apollo3.ApolloClient
import com.toptal.data.network.model.repository_details.RepositoryDetailsMapper
import com.toptal.data.network.model.repository_node.RepositoryClientImpl
import com.toptal.data.network.model.repository_node.RepositoryNodeMapper
import com.toptal.domain.usecases.repositories.RepositoryClient
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
        repositoryNodeMapper: RepositoryNodeMapper,
        repositoryDetailsMapper: RepositoryDetailsMapper
    ): RepositoryClient {
        return RepositoryClientImpl(
            apolloClient,
            repositoryNodeMapper,
            repositoryDetailsMapper
        )
    }
}