package com.example.kotoba.ui.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotoba.ui.theme.KotobaTheme
import com.example.kotoba.ui.theme.Spacing

@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = category,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        modifier = modifier
            .defaultMinSize(minHeight = Spacing.minTouchTarget),
        shape = RoundedCornerShape(Spacing.medium),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryChipSelectedPreview() {
    KotobaTheme(darkTheme = false) {
        CategoryChip(
            category = "N5",
            isSelected = true,
            onClick = {},
            modifier = Modifier.padding(Spacing.small),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryChipUnselectedPreview() {
    KotobaTheme(darkTheme = true) {
        CategoryChip(
            category = "N4",
            isSelected = false,
            onClick = {},
            modifier = Modifier.padding(Spacing.small),
        )
    }
}
