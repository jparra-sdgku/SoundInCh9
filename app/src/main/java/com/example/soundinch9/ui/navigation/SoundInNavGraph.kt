package com.example.soundinch9.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.soundinch9.ui.UserSessionViewModel
import com.example.soundinch9.ui.screens.LoginScreen
import com.example.soundinch9.ui.screens.MainScreen
import com.example.soundinch9.ui.screens.PlaylistDetailScreen
import com.example.soundinch9.ui.screens.RegisterScreen

@Composable
fun SoundInNavGraph(
    navController: NavHostController,
    sessionViewModel: UserSessionViewModel
) {
    NavHost(
        navController = navController,
        startDestination = SoundInRoutes.LOGIN
    ) {
        composable(SoundInRoutes.LOGIN) {
            LoginScreen(
                sessionViewModel = sessionViewModel,
                onNavigateToRegister = {
                    navController.navigate(SoundInRoutes.REGISTER)
                },
                onLoginSuccess = {
                    navController.navigate(SoundInRoutes.MAIN) {
                        popUpTo(SoundInRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        } // End Login composable
        composable(SoundInRoutes.MAIN) {
            MainScreen(
                sessionViewModel = sessionViewModel,
                onLogout = {
                    sessionViewModel.logout()
                    navController.navigate(SoundInRoutes.LOGIN) {
                        popUpTo(SoundInRoutes.MAIN) { inclusive = true }
                    }
                },
                onNavigateToPlaylistDetail = { playlist ->
                    navController.navigate("playlistDetail/${playlist.id}")

                }
            )
        } // End Main composable
        composable(SoundInRoutes.REGISTER) {
            RegisterScreen()
        } // End Register composable
        composable(
            route = SoundInRoutes.PLAYLIST_DETAIL,
            arguments = listOf(navArgument("playlistId") { type = NavType.IntType })
        ) { backStackEntry ->
            PlaylistDetailScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

















