# AI Usage Report (SIS 3 Assignment)

## 1. AI Tools Used & Purposes
- **Android Studio AI Assistant (Gemini / Claude)**: Used for initial Jetpack Compose component scaffold generation, theme color scheme setup, and generating boilerplate Compose Navigation setup.

## 2. Three Most Useful Prompts
1. *"Create a clean Material 3 Jetpack Compose theme with light and dark mode support using custom Indigo and Sakura color palettes in Color.kt and Theme.kt."*
2. *"Build a reusable WordCard composable in Jetpack Compose that displays Japanese Kanji, Kana, and English meaning with maxLines = 1 and TextOverflow.Ellipsis, with a minimum touch target size of 48dp."*
3. *"Set up Navigation Compose NavHost for 3 screens: VocabularyListScreen, WordDetailScreen passing wordId as route argument, and ProgressScreen with bottom navigation bar."*

## 3. Case Where AI Was Inconsistent / Wrong and How It Was Fixed
**Issue**: The AI initially generated layout components using hardcoded hex color values (`Color(0xFF1E3A8A)`) and hardcoded padding numbers (`16.dp`, `8.dp`) directly inside composables, as well as using deprecated APIs (`ScrollableTabRow`).
**How I Noticed**: I reviewed the assignment requirements in section 5.2, which strictly forbids hardcoded colors or direct dp padding inside screen composables.
**How I Fixed It**: 
1. Created `Spacing.kt` to centralize all padding and spacing values (`Spacing.small`, `Spacing.medium`, `Spacing.minTouchTarget`).
2. Updated all screens and reusable composables to reference `MaterialTheme.colorScheme` (`primary`, `onSurface`, `surfaceVariant`) and `MaterialTheme.typography` instead of literal values.
3. Updated navigation components to use standard `PrimaryScrollableTabRow` and `NavigationBar`.

## 4. What Was Written or Modified By Hand
- Defined the domain data class `Word` and 12 curated Japanese vocabulary entries (`sampleWords`).
- Designed the `Spacing.kt` system for strict architectural consistency.
- Configured Navigation Compose routing with type-safe arguments and backstack state handling.
- Implemented state-based category filtering and the `EmptyState` composable when filtered results are empty.
- Configured interactive state toggling for "Mark as Learned" in `WordDetailScreen` using `remember { mutableStateOf(...) }`.
