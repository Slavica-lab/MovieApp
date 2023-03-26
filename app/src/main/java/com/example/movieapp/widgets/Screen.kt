package com.example.movieapp.widgets

sealed class Screen(val route: String){

    object Home : Screen("homescreen")
    object Favorites : Screen("favoritescreen")
    object Details : Screen("detailscreen/{movieId}"){
        fun createRoute(movieId: String) = "detailscreen/$movieId"
    }

}