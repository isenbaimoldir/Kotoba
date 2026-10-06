package com.example.kotoba.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotoba.R
import com.example.kotoba.data.Word
import com.example.kotoba.data.sampleWords
import com.example.kotoba.ui.theme.KotobaTheme
import com.example.kotoba.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordDetailScreen(
    wordId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    words: List<Word> = sampleWords,
) {
    val word = remember(wordId, words) {
        words.find { it.id == wordId } ?: words.first()
    }

    var isLearnedState by remember(word) { mutableStateOf(word.isLearned) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_word_detail),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(Spacing.minTouchTarget),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = stringResource(R.string.cd_back),
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
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
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(Spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Spacing.extraLarge * 6)
                    .clip(RoundedCornerShape(Spacing.medium))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = word.imageResId ?: R.drawable.ic_image),
                    contentDescription = stringResource(R.string.cd_word_icon, word.kanji),
                    modifier = Modifier.size(Spacing.extraLarge * 3),
                )
            }

            Spacer(modifier = Modifier.height(Spacing.medium))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(Spacing.medium),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            ) {
                Column(
                    modifier = Modifier.padding(Spacing.medium),
                ) {
                    DetailRow(
                        label = stringResource(R.string.label_kanji),
                        value = word.kanji,
                        isHeadline = true,
                    )

                    DetailRow(
                        label = stringResource(R.string.label_kana),
                        value = word.kana,
                    )

                    DetailRow(
                        label = stringResource(R.string.label_meaning),
                        value = word.english,
                    )

                    DetailRow(
                        label = stringResource(R.string.label_category),
                        value = word.category,
                    )

                    DetailRow(
                        label = stringResource(R.string.label_status),
                        value = if (isLearnedState) {
                            stringResource(R.string.stats_learned)
                        } else {
                            stringResource(R.string.stats_unlearned)
                        },
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            Button(
                onClick = { isLearnedState = !isLearnedState },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = Spacing.minTouchTarget),
                shape = RoundedCornerShape(Spacing.medium),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isLearnedState) {
                        MaterialTheme.colorScheme.secondary
                    } else {
                        MaterialTheme.colorScheme.primary
                    },
                ),
            ) {
                Text(
                    text = if (isLearnedState) {
                        stringResource(R.string.label_mark_unlearned)
                    } else {
                        stringResource(R.string.label_mark_learned)
                    },
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    isHeadline: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Text(
            text = value,
            style = if (isHeadline) {
                MaterialTheme.typography.headlineMedium
            } else {
                MaterialTheme.typography.titleMedium
            },
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WordDetailScreenLightPreview() {
    KotobaTheme(darkTheme = false) {
        WordDetailScreen(wordId = "1", onBackClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun WordDetailScreenDarkPreview() {
    KotobaTheme(darkTheme = true) {
        WordDetailScreen(wordId = "2", onBackClick = {})
    }
}
