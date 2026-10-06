package com.example.kotoba.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotoba.R
import com.example.kotoba.data.Word
import com.example.kotoba.data.sampleWords
import com.example.kotoba.ui.components.CategoryChip
import com.example.kotoba.ui.components.EmptyState
import com.example.kotoba.ui.components.WordCard
import com.example.kotoba.ui.theme.KotobaTheme
import com.example.kotoba.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyListScreen(
    onWordClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    words: List<Word> = sampleWords,
) {
    val categories = listOf(
        stringResource(R.string.category_all),
        "N5",
        "N4",
        "N3",
        "N2",
        "N1",
    )
    var selectedCategory by remember { mutableStateOf(categories.first()) }

    val filteredWords = remember(selectedCategory, words) {
        if (selectedCategory == categories.first()) {
            words
        } else {
            words.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_vocabulary_list),
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = Spacing.medium, vertical = Spacing.small),
                horizontalArrangement = Arrangement.spacedBy(Spacing.small),
            ) {
                items(categories) { category ->
                    CategoryChip(
                        category = category,
                        isSelected = category == selectedCategory,
                        onClick = { selectedCategory = category },
                    )
                }
            }

            if (filteredWords.isEmpty()) {
                EmptyState(
                    title = stringResource(R.string.empty_vocabulary_title),
                    message = stringResource(R.string.empty_vocabulary_message),
                    modifier = Modifier.weight(1f),
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(Spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(Spacing.small),
                ) {
                    items(
                        items = filteredWords,
                        key = { it.id },
                    ) { word ->
                        WordCard(
                            word = word,
                            onClick = { onWordClick(word.id) },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VocabularyListScreenLightPreview() {
    KotobaTheme(darkTheme = false) {
        VocabularyListScreen(onWordClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun VocabularyListScreenDarkPreview() {
    KotobaTheme(darkTheme = true) {
        VocabularyListScreen(onWordClick = {})
    }
}
