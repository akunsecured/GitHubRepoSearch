package dev.akunsecured.githubreposearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.akunsecured.githubreposearch.data.remote.GitHubApi
import dev.akunsecured.githubreposearch.data.repository.GitHubRepoRepositoryImpl
import dev.akunsecured.githubreposearch.domain.repository.GitHubRepoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGitHubRepoRepository(api: GitHubApi): GitHubRepoRepository {
        return GitHubRepoRepositoryImpl(api)
    }
}