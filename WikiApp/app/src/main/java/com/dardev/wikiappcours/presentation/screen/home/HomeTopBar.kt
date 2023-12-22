package com.dardev.wikiappcours.presentation.screen.home

import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dardev.wikiappcours.R
import com.dardev.wikiappcours.navigation.Screen
import com.dardev.wikiappcours.ui.theme.topAppBarBackgroundColor
import com.dardev.wikiappcours.ui.theme.topAppBarContentColor


@Composable
fun HomeTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.explore_character),
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = 24.sp
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = {
                navController.navigate(Screen.Search.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        }
    )
}