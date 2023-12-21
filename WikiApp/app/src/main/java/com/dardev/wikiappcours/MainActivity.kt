package com.dardev.wikiappcours

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dardev.wikiappcours.navigation.NavModel
import com.dardev.wikiappcours.ui.theme.WikiAppCoursTheme

class MainActivity : ComponentActivity() {
    private lateinit var  navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WikiAppCoursTheme {
               navController = rememberNavController()
                NavModel(navController = navController)
            }
        }
    }
}