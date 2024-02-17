package dev.akunsecured.githubreposearch.presentation.repo_detail.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle

@Composable
fun LinkWidget(
    url: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = AnnotatedString(url),
        onClick = {
            uriHandler.openUri(url)
        },
        style = TextStyle.Default.copy(
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}