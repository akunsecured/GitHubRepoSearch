package dev.akunsecured.githubreposearch.presentation.repo_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
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
import dev.akunsecured.githubreposearch.presentation.repo_detail.components.NumericDataWidget

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
            text = repo.fullName,
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
            NumericDataWidget(
                icon = Icons.TwoTone.Star,
                count = repo.stargazersCount,
                text = "stars",
            )
            Text(
                text = "Last updated at: ${Constants.DISPLAY_DATA_FORMATTER.format(repo.updatedAt)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}