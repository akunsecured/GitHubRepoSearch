package dev.akunsecured.githubreposearch.domain.use_case.search_repos

import dev.akunsecured.githubreposearch.common.Resource
import dev.akunsecured.githubreposearch.data.remote.dto.toRepo
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.domain.repository.GitHubRepoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchReposUseCase @Inject constructor(
    private val repository: GitHubRepoRepository
) {

    operator fun invoke(text: String, page: Int): Flow<Resource<List<Repo>>> = flow {
        try {
            emit(Resource.Loading())
            val repos = repository.getRepos(text, page).map { it.toRepo() }
            emit(Resource.Success(repos))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected network error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}