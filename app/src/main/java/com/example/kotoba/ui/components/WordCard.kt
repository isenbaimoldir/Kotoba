package com.example.kotoba.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotoba.R
import com.example.kotoba.data.Word
import com.example.kotoba.ui.theme.KotobaTheme
import com.example.kotoba.ui.theme.Spacing

@Composable
fun WordCard(
    word: Word,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = Spacing.minTouchTarget)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape = RoundedCornerShape(Spacing.small),
        elevation = CardDefaults.cardElevation(defaultElevation = Spacing.extraSmall),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(Spacing.minTouchTarget)
                    .clip(RoundedCornerShape(Spacing.small))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = word.imageResId ?: R.drawable.ic_image),
                    contentDescription = stringResource(R.string.cd_word_icon, word.kanji),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            Spacer(modifier = Modifier.width(Spacing.medium))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = word.kanji,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.width(Spacing.small))
                    Text(
                        text = "(${word.kana})",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Text(
                    text = word.english,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.width(Spacing.small))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Spacing.extraSmall))
                    .background(
                        if (word.isLearned) {
                            MaterialTheme.colorScheme.secondary
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        },
                    )
                    .padding(horizontal = Spacing.small, vertical = Spacing.extraSmall),
            ) {
                Text(
                    text = if (word.isLearned) stringResource(R.string.label_learned_chip) else word.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (word.isLearned) {
                        MaterialTheme.colorScheme.onSecondary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordCardLightPreview() {
    KotobaTheme(darkTheme = false) {
        WordCard(
            word = Word(
                id = "1",
                kanji = "猫",
                kana = "ねこ",
                english = "Cat",
                category = "N5",
                isLearned = false,
                imageResId = R.drawable.ic_cat,
            ),
            onClick = {},
            modifier = Modifier.padding(Spacing.medium),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WordCardDarkPreview() {
    KotobaTheme(darkTheme = true) {
        WordCard(
            word = Word(
                id = "2",
                kanji = "犬",
                kana = "いぬ",
                english = "Dog",
                category = "N5",
                isLearned = true,
                imageResId = R.drawable.ic_dog,
            ),
            onClick = {},
            modifier = Modifier.padding(Spacing.medium),
        )
    }
}
