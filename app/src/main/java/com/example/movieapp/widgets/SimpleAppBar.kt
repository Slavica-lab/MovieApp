package com.example.movieapp.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController

@Composable
fun SimpleAppBar(navController: NavController, title: String, showBackButton: Boolean = false, showActions: Boolean = true) {
    var expanded by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(title)
        },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon =
        if (showBackButton) {
            @Composable {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                }
            }
        } else null,
        actions = {
            if (showActions) {
                IconButton(onClick = {
                    expanded = true
                }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = null)
                }
                DropdownMenu(
                    modifier = Modifier.width(width = 150.dp),
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    properties = PopupProperties()
                ) {
                    DropdownMenuItem(onClick = { navController.navigate(Screen.Favorites.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                        Spacer(modifier = Modifier.width(width = 8.dp))
                        Text("Favorites")
                    }
                }
            }})
}
