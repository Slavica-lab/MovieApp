package com.example.movieapp.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie


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
fun MovieList(navController: NavController, movies: List<Movie>) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie) { movieId ->
                navController.navigate(Screen.Details.createRoute(movieId))
            }
        }
    }
}
