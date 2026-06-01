package com.example.beon.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beon.feature.contentdetail.ContentDetailScreen
import com.example.beon.feature.contentdetail.ContentDetailViewModel
import com.example.beon.feature.home.HomeScreen
import com.example.beon.feature.home.HomeViewModel
import com.example.beon.feature.player.PlayerScreen
import com.example.beon.feature.player.PlayerViewModel

object BeOnRoutes {
    const val Home = "home"
    const val ContentDetail = "content/{contentId}"
    const val Player = "player/{contentId}"

    fun contentDetail(contentId: String) = "content/${Uri.encode(contentId)}"

    fun player(contentId: String) = "player/${Uri.encode(contentId)}"
}

private fun String.decodeNavArg(): String = Uri.decode(this)

@Composable
fun BeOnNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BeOnRoutes.Home,
    ) {
        composable(BeOnRoutes.Home) {
            val viewModel: HomeViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(
                catalog = uiState.catalog,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                onContentClick = { contentId ->
                    navController.navigate(BeOnRoutes.contentDetail(contentId))
                },
            )
        }

        composable(
            route = BeOnRoutes.ContentDetail,
            arguments = listOf(
                navArgument("contentId") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val contentId = backStackEntry.arguments?.getString("contentId").orEmpty().decodeNavArg()
            val viewModel: ContentDetailViewModel = viewModel(key = contentId) {
                ContentDetailViewModel(contentId)
            }
            val uiState by viewModel.uiState.collectAsState()
            ContentDetailScreen(
                content = uiState.content,
                relatedContent = uiState.relatedContent,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                onBack = { navController.popBackStack() },
                onPlayClick = {
                    navController.navigate(BeOnRoutes.player(contentId))
                },
                onContentClick = { relatedId ->
                    navController.navigate(BeOnRoutes.contentDetail(relatedId))
                },
            )
        }

        composable(
            route = BeOnRoutes.Player,
            arguments = listOf(
                navArgument("contentId") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val contentId = backStackEntry.arguments?.getString("contentId").orEmpty().decodeNavArg()
            val viewModel: PlayerViewModel = viewModel(key = "player-$contentId") {
                PlayerViewModel(contentId)
            }
            val uiState by viewModel.uiState.collectAsState()
            PlayerScreen(
                uiState = uiState,
                onBack = { navController.popBackStack() },
                onRecommendationClick = { relatedId ->
                    navController.navigate(BeOnRoutes.player(relatedId)) {
                        popUpTo(BeOnRoutes.player(contentId)) { inclusive = true }
                    }
                },
            )
        }
    }
}
