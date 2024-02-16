package dev.akunsecured.githubreposearch.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.akunsecured.githubreposearch.common.Constants
import dev.akunsecured.githubreposearch.data.remote.GitHubApi
import dev.akunsecured.githubreposearch.data.remote.dto.toRepo
import dev.akunsecured.githubreposearch.domain.model.Repo
import retrofit2.HttpException
import java.io.IOException

class RepoPagingSource(
    private val api: GitHubApi,
    private val text: String,
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val currentPage = params.key ?: 1
            val result = api.searchRepositories(
                text = text,
                page = currentPage,
                perPage = Constants.MAX_PAGE_SIZE,
            )
            LoadResult.Page(
                data = result.items.map { it.toRepo() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (result.items.isEmpty()) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition
    }
}