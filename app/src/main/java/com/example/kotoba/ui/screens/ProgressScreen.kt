package com.example.kotoba.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotoba.R
import com.example.kotoba.data.Word
import com.example.kotoba.data.sampleWords
import com.example.kotoba.ui.components.SectionHeader
import com.example.kotoba.ui.theme.KotobaTheme
import com.example.kotoba.ui.theme.Spacing

data class CategoryDeckStats(
    val category: String,
    val totalWords: Int,
    val learnedWords: Int,
) {
    val progressPercentage: Int
        get() = if (totalWords > 0) (learnedWords * 100) / totalWords else 0
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier,
    words: List<Word> = sampleWords,
) {
    val totalWords = words.size
    val learnedWords = words.count { it.isLearned }
    val unlearnedWords = totalWords - learnedWords

    val categoryDeckStats = listOf("N5", "N4", "N3", "N2", "N1").map { category ->
        val categoryWords = words.filter { it.category.equals(category, ignoreCase = true) }
        CategoryDeckStats(
            category = category,
            totalWords = categoryWords.size,
            learnedWords = categoryWords.count { it.isLearned },
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_progress),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium),
        ) {
            item {
                SectionHeader(
                    title = stringResource(R.string.stats_total),
                    subtitle = "Overall learning statistics across all JLPT decks",
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(Spacing.medium),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spacing.medium),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        StatBox(
                            label = stringResource(R.string.stats_total),
                            value = totalWords.toString(),
                        )

                        StatBox(
                            label = stringResource(R.string.stats_learned),
                            value = learnedWords.toString(),
                        )

                        StatBox(
                            label = stringResource(R.string.stats_unlearned),
                            value = unlearnedWords.toString(),
                        )
                    }
                }
            }

            item {
                SectionHeader(
                    title = stringResource(R.string.stats_deck_progress),
                    subtitle = "Breakdown by JLPT level",
                )
            }

            items(categoryDeckStats) { stats ->
                DeckProgressCard(stats = stats)
            }
        }
    }
}

@Composable
private fun StatBox(
    label: String,
    value: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(modifier = Modifier.height(Spacing.extraSmall))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

@Composable
private fun DeckProgressCard(
    stats: CategoryDeckStats,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Spacing.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = "JLPT ${stats.category} Deck",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "${stats.learnedWords} / ${stats.totalWords} words learned",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
            ) {
                Text(
                    text = "${stats.progressPercentage}%",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = Spacing.medium, vertical = Spacing.small),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenLightPreview() {
    KotobaTheme(darkTheme = false) {
        ProgressScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenDarkPreview() {
    KotobaTheme(darkTheme = true) {
        ProgressScreen()
    }
}
