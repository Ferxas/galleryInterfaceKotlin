package com.ferxas.chris_subiabre_seccioncurso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "gallery") {
        composable("gallery") {
            GalleryScreen(navController)
        }
        composable("fullscreen/{imageId}") { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")?.toIntOrNull()
            if (imageId != null) {
                FullScreenImageScreen(imageId)
            }
        }
    }
}

@Composable
fun GalleryScreen(navController: NavController) {
    val images = listOf(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4
    )

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Text(
            text = "Galería de Arte",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            color = Color.Black
        )
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            itemsIndexed(images) { _, image ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "Gallery Image",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .clickable {
                                navController.navigate("fullscreen/$image")
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun FullScreenImageScreen(imageId: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Full Screen Image",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
