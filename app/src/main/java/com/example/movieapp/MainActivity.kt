package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.models.Movie
import com.example.movieapp.ui.theme.MovieAppTheme
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MainNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                MainNavigation()
                }
            }
        }
    }



