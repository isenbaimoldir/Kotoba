package com.example.kotoba.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotoba.ui.theme.KotobaTheme
import com.example.kotoba.ui.theme.Spacing

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.small),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
        )
        if (!subtitle.isNullOrEmpty()) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = Spacing.extraSmall),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SectionHeaderLightPreview() {
    KotobaTheme(darkTheme = false) {
        SectionHeader(
            title = "JLPT Categories",
            subtitle = "Select a category to filter vocabulary",
            modifier = Modifier.padding(Spacing.medium),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SectionHeaderDarkPreview() {
    KotobaTheme(darkTheme = true) {
        SectionHeader(
            title = "Deck Statistics",
            subtitle = "Track your learning progress",
            modifier = Modifier.padding(Spacing.medium),
        )
    }
}
