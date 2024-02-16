package dev.akunsecured.githubreposearch.presentation.repo_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RepoSearchTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    onButtonClicked: () -> Unit,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            placeholder = { Text("Search...") },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        OutlinedIconButton(
            enabled = text.isNotBlank(),
            onClick = onButtonClicked,
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search"
            )
        }
    }
}