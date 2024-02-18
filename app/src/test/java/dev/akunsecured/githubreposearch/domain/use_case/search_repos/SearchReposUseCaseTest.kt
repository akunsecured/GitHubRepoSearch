package dev.akunsecured.githubreposearch.domain.use_case.search_repos

import androidx.paging.AsyncPagingDataDiffer
import dev.akunsecured.githubreposearch.data.repository.FakeGitHubRepoRepository
import dev.akunsecured.githubreposearch.domain.model.Owner
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.utils.MainDispatcherRule
import dev.akunsecured.githubreposearch.utils.TestDiffCallback
import dev.akunsecured.githubreposearch.utils.TestListCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class SearchReposUseCaseTest {

    private lateinit var searchRepos: SearchReposUseCase
    private lateinit var fakeRepository: FakeGitHubRepoRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        fakeRepository = FakeGitHubRepoRepository()
        searchRepos = SearchReposUseCase(fakeRepository)
    }

    private fun createFakeRepoEntity(index: Int, namePrefix: String): Repo {
        val name = "$namePrefix-$index"
        return Repo(
            id = index,
            name = name,
            fullName = "$index/$name",
            description = index.toString(),
            htmlUrl = index.toString(),
            stargazersCount = index,
            forksCount = index,
            createdAt = Date(),
            updatedAt = Date(),
            owner = Owner(
                name = index.toString(),
                avatarUrl = index.toString(),
                htmlUrl = index.toString()
            )
        )
    }

    @Test
    fun `Search repos by name`() = runBlocking {
        val text = "Android"

        val expectedList = mutableListOf<Repo>()
        (1..10).forEach { index ->
            expectedList.add(
                createFakeRepoEntity(
                    index,
                    "Android-Repository"
                )
            )
        }

        fakeRepository.insertRepos(expectedList)
        fakeRepository.insertRepos(
            listOf(
                createFakeRepoEntity(
                    11,
                    "iOS-Repository"
                )
            )
        )

        fakeRepository.getRepos(text).collect { actualPagingData ->
            val differ = AsyncPagingDataDiffer(
                diffCallback = TestDiffCallback<Repo>(),
                updateCallback = TestListCallback(),
                workerDispatcher = Dispatchers.Main
            )
            differ.submitData(actualPagingData)
            val actualList = differ.snapshot().items

            assert(actualList.size == expectedList.size)

            for (i in actualList.indices) {
                assert(actualList[i] == expectedList[i])
            }
        }
    }
}