package dev.akunsecured.githubreposearch.presentation.repo_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.akunsecured.githubreposearch.common.Constants
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.presentation.repo_detail.components.LinkWidget
import dev.akunsecured.githubreposearch.presentation.repo_detail.components.NumericDataWidget

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Destination
@Composable
fun RepoDetailScreen(
    navigator: DestinationsNavigator,
    repo: Repo,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = repo.name) },
                navigationIcon =
                {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }

            )
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Owner",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(repo.owner.avatarUrl),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = repo.owner.name,
                        )
                        LinkWidget(
                            url = repo.owner.htmlUrl
                        )
                    }
                }

                // Repo info
                Text(
                    text = "About the repository",
                    style = MaterialTheme.typography.headlineSmall
                )
                LinkWidget(
                    url = repo.htmlUrl,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                if (!repo.description.isNullOrBlank()) {
                    Text(
                        text = repo.description,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                ) {
                    NumericDataWidget(
                        icon = Icons.TwoTone.Star,
                        count = repo.stargazersCount,
                        text = "stars",
                    )
                    NumericDataWidget(
                        icon = Icons.TwoTone.Share,
                        count = repo.forksCount,
                        text = "forks",
                    )
                }
                Column {
                    Text(
                        text = "Created at: ${Constants.DISPLAY_DATA_FORMATTER.format(repo.createdAt)}",
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                    Text(text = "Last updated at: ${Constants.DISPLAY_DATA_FORMATTER.format(repo.updatedAt)}")
                }
            }
        }
    )
}