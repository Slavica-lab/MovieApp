package com.example.movieapp.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            TopAppBarGlobal(navController, "Movies")
            MovieList(navController, getMovies())
        }
    }
}


@Composable
fun MovieRow(movie: Movie, onItemClick: (movieId: String) -> Unit) {
    var isToggled by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(20.dp)
            .clickable { onItemClick(movie.id) },
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                //Image(painter = painterResource(id = R.drawable.avatar), contentDescription = null)
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = movie.images[0],
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movie.title, fontSize = 20.sp)
                Icon(
                    imageVector = if (isToggled) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = null,
                    modifier = Modifier.clickable { isToggled = !isToggled }
                )
            }
            if (isToggled) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Director: ${movie.director}")
                    Text("Released: ${movie.year}")
                    Text("Genre: ${movie.genre}")
                    Text("Actors: ${movie.actors}")
                    Text("Rating: ${movie.rating}")
                    Spacer(modifier = Modifier.height(height = 8.dp))
                    Divider(thickness = 1.dp, color = MaterialTheme.colors.primary)
                    Spacer(modifier = Modifier.height(height = 8.dp))
                    Text(
                        text = "Plot: ${movie.plot}", Modifier.padding(5.dp),
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBarGlobal(navController: NavController, title: String, showBackButton: Boolean = false, showActions: Boolean = true) {
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
            @Composable{
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
            }}
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
                DropdownMenuItem(onClick = { navController.navigate("favoritescreen") }) {
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


@Composable
fun MovieList(navController: NavController, movies: List<Movie>) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie) { movieId ->
                navController.navigate(route = "detailscreen/$movieId")
            }
        }
    }
}
