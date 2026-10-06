# App Screen Design Sketches

This directory contains design sketches and structural planning for the Kotoba Japanese Vocabulary app as required by Part A of the assignment.

## Screen 1: Vocabulary List Screen
- **Outer Layout**: `Scaffold`
- **Top Bar**: `TopAppBar` (Title: "Kotoba Vocabulary")
- **Main Container**: `Column`
  - **Category Row**: `LazyRow` containing `CategoryChip` items ("All", "N5", "N4", "N3", "N2", "N1")
  - **Vocabulary List**: `LazyColumn` containing `WordCard` items
    - Each `WordCard`: `Card` -> `Row` -> `Box` (Image Icon) + `Column` (Kanji, Kana, English) + `Box` (JLPT Level / Learned Chip)
  - **Empty State**: `EmptyState` composable displayed when category filtering yields 0 words.

## Screen 2: Word Detail Screen
- **Outer Layout**: `Scaffold`
- **Top Bar**: `TopAppBar` with Back navigation button (`onBackClick`)
- **Main Container**: `Column` (scrollable)
  - **Hero Illustration Box**: `Box` + `Image` with meaningful `contentDescription`
  - **Details Card**: `Card` -> `Column` containing `DetailRow` items (Kanji, Kana, Meaning, JLPT Level, Status)
  - **Interactive Action**: `Button` toggling "Mark as Learned" / "Mark as Unlearned" state using `remember { mutableStateOf(...) }`

## Screen 3: Progress Screen
- **Outer Layout**: `Scaffold`
- **Top Bar**: `TopAppBar` (Title: "Progress & Stats")
- **Main Container**: `LazyColumn`
  - **Header**: `SectionHeader`
  - **Overview Stats Card**: `Card` -> `Row` -> `StatBox` (Total Words, Learned Words, To Learn)
  - **Deck Breakdown Header**: `SectionHeader`
  - **Deck List**: `items(categoryDeckStats)` -> `DeckProgressCard`
