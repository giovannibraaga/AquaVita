package com.fiap.aquavita.composable.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fiap.aquavita.models.Article
import com.fiap.aquavita.ui.theme.Placeholder
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.ui.theme.TitleNews

@Composable
fun NewsCard(article: Article, onClick: (String?) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(article.url) }
    ) {
        Column {
            article.urlToImage?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                )
            }
            Column(Modifier.padding(16.dp)) {
                Text(
                    article.title ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = TitleNews, fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    article.description ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextDefault,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(8.dp))
                article.publishedAt?.let {
                    val date = it.take(10)
                    Text(date, style = MaterialTheme.typography.labelSmall, color = Placeholder)
                }
            }
        }
    }
}