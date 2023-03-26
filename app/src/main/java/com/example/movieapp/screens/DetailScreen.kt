package com.example.movieapp.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.MovieRow
import com.example.movieapp.widgets.SimpleAppBar

@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    var movie = getMovies().first { it.id == movieId }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            SimpleAppBar(navController, movie.title, true, false)
            MovieRow(movie = movie){}
            MovieImages(navController = navController, images = movie.images)
        }
    }
}

@Composable
fun MovieImages(navController: NavController, images: List<String>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.Center){
        Text("Movie Images",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
    }
    LazyRow {
        //items is a for each loop
        //image is saved as String with path
        items(images) { image ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .padding(20.dp),
                elevation = 5.dp,
                shape = RoundedCornerShape(5.dp)
            ){
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            }
        }
        }
    }