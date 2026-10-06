# Kotoba - Japanese Vocabulary App

Kotoba is a modern Android vocabulary learning application built with **Kotlin** and **Jetpack Compose (Material 3)**.

## Features & Rubric Implementation

### 1. Screens & Layouts
- **Vocabulary List Screen**: Displays JLPT vocabulary items in a vertical `LazyColumn`. Features a horizontal `LazyRow` of JLPT category chips ("All", "N5", "N4", "N3", "N2", "N1") for interactive filtering. Shows a friendly `EmptyState` composable if filtering yields no results.
- **Word Detail Screen**: Displays detailed Japanese word breakdown (Kanji, Kana, English meaning, JLPT Level, Learned status) along with an image illustration resource (`contentDescription`). Features an interactive "Mark as Learned" toggle powered by `remember { mutableStateOf(...) }`.
- **Progress & Stats Screen**: Shows overall learning statistics (total, learned, unlearned) and a breakdown of deck progress across JLPT levels using `LazyColumn` and `Card` containers.

### 2. Styling & Design System
- **Theme**: Custom Material 3 color palette in `Color.kt` and `Theme.kt` with full Light and Dark mode support.
- **Spacing**: Centralized spacing system in `Spacing.kt` (`Spacing.small`, `Spacing.medium`, `Spacing.large`, `Spacing.minTouchTarget` = 48dp). No hardcoded colors or direct dp fonts in screen composables.
- **Typography**: Uses `MaterialTheme.typography` across all components.

### 3. Reusable Components
- `WordCard`: Displays Kanji, Kana, and English with `maxLines = 1` and `TextOverflow.Ellipsis`.
- `CategoryChip`: JLPT filter chip.
- `SectionHeader`: Section title header.
- `EmptyState`: Empty list placeholder with friendly message.

### 4. Navigation & Architecture
- Built with **Navigation Compose** (`NavHost`, `rememberNavController`).
- Detail screen receives `wordId` as a navigation route argument (`"detail/{wordId}"`).
- Features a bottom `NavigationBar` for seamless switching between Vocabulary and Progress tabs.

## Project Structure
```
com.example.kotoba/
├── data/
│   └── Word.kt
├── ui/
│   ├── components/
│   │   ├── CategoryChip.kt
│   │   ├── EmptyState.kt
│   │   ├── SectionHeader.kt
│   │   └── WordCard.kt
│   ├── navigation/
│   │   └── KotobaNavGraph.kt
│   ├── screens/
│   │   ├── ProgressScreen.kt
│   │   ├── VocabularyListScreen.kt
│   │   └── WordDetailScreen.kt
│   └── theme/
│       ├── Color.kt
│       ├── KotobaAppScreen.kt
│       ├── Spacing.kt
│       ├── Theme.kt
│       └── Type.kt
├── MainActivity.kt
└── AI_USAGE.md
```
