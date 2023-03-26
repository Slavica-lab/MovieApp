package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp.models.getFavorites
import com.example.movieapp.models.getMovies

@Composable
fun FavoriteScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopAppBarGlobal(navController, "Favorites", true, false)
            MovieList(navController = navController, movies = getFavorites())
        }
    }
}
