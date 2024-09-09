package com.toptal.githubrepo.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.toptal.githubrepo.BuildConfig
import com.toptal.githubrepo.data.network.AuthInterceptor
import com.toptal.githubrepo.data.network.model.repository_details.RepositoryDetailsImpl
import com.toptal.githubrepo.data.network.model.repository_details.RepositoryDetailsMapper
import com.toptal.githubrepo.data.network.model.repository_node.RepositoryNodeClientImpl
import com.toptal.githubrepo.data.network.model.repository_node.RepositoryNodeMapper
import com.toptal.githubrepo.domain.model.repository_nodes.RepositoryNode
import com.toptal.githubrepo.domain.usecases.repository_details.RepositoryDetailsClient
import com.toptal.githubrepo.domain.usecases.repository_nodes.RepositoryNodeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(BuildConfig.GITHUB_TOKEN)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BuildConfig.GITHUB_API)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRepositoryNodeClient(
        apolloClient: ApolloClient,
        mapper: RepositoryNodeMapper
    ): RepositoryNodeClient {
        return RepositoryNodeClientImpl(apolloClient, mapper)
    }

    @Provides
    @Singleton
    fun provideRepositoryDetailsClient(
        apolloClient: ApolloClient,
        mapper: RepositoryDetailsMapper
    ): RepositoryDetailsClient {
        return RepositoryDetailsImpl(apolloClient, mapper)
    }
}