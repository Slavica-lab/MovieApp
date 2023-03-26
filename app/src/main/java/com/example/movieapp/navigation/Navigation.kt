package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.FavoriteScreen
import com.example.movieapp.screens.HomeScreen


@Composable
fun MainNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "homescreen"
    ) {
        composable(route = "homescreen") {
            HomeScreen(navController)
        }
        composable(
            route = "detailscreen/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) {backStackEntry ->
            DetailScreen(navController, backStackEntry.arguments?.getString("movieId"))
        }
        composable(route = "favoritescreen") {
            FavoriteScreen(navController)
        }
    }
}