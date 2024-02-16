package dev.akunsecured.githubreposearch.presentation.repo_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.akunsecured.githubreposearch.common.Constants
import dev.akunsecured.githubreposearch.domain.model.Repo

@Composable
fun RepoListItem(
    repo: Repo,
    onItemClick: (Repo) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(repo) }
            .padding(16.dp),
    ) {
        Text(
            text = repo.name,
            style = MaterialTheme.typography.titleLarge,
            overflow = TextOverflow.Ellipsis,
        )
        if (repo.description != null) {
            Text(
                text = repo.description,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
                Text(
                    text = repo.stargazersCount.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Text(
                text = "Last update: ${Constants.DISPLAY_DATA_FORMATTER.format(repo.updatedAt)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}