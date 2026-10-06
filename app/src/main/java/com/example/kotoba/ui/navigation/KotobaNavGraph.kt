package com.example.kotoba.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotoba.R
import com.example.kotoba.ui.screens.ProgressScreen
import com.example.kotoba.ui.screens.VocabularyListScreen
import com.example.kotoba.ui.screens.WordDetailScreen

sealed class Screen(
    val route: String,
    val titleResId: Int,
    val iconResId: Int,
) {
    object Vocabulary : Screen("vocabulary", R.string.nav_vocabulary, R.drawable.ic_image)
    object Progress : Screen("progress", R.string.nav_progress, R.drawable.ic_star)
    object Detail : Screen("detail/{wordId}", R.string.title_word_detail, R.drawable.ic_image) {
        fun createRoute(wordId: String): String = "detail/$wordId"
    }
}

@Composable
fun KotobaNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        Screen.Vocabulary,
        Screen.Progress,
    )

    val showBottomBar = bottomNavItems.any { it.route == currentRoute }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.iconResId),
                                    contentDescription = stringResource(screen.titleResId),
                                )
                            },
                            label = { Text(text = stringResource(screen.titleResId)) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            ),
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Vocabulary.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = Screen.Vocabulary.route) {
                VocabularyListScreen(
                    onWordClick = { wordId ->
                        navController.navigate(Screen.Detail.createRoute(wordId))
                    },
                )
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("wordId") { type = NavType.StringType },
                ),
            ) { backStackEntry ->
                val wordId = backStackEntry.arguments?.getString("wordId") ?: "1"
                WordDetailScreen(
                    wordId = wordId,
                    onBackClick = {
                        navController.navigateUp()
                    },
                )
            }

            composable(route = Screen.Progress.route) {
                ProgressScreen()
            }
        }
    }
}
