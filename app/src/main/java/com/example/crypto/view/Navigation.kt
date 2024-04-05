package com.example.crypto.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    )
        {
        Text(text = "Home Screen")
            Spacer(modifier = Modifier.size(20.dp))
            Button(onClick = {
                navController.navigate(BottomNavItem.Favourites.route)
            }) {
                Text(text = "Ir a Favoritos")
            }
    }
}

@Composable
fun Favourites() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Text(text = "Favourites Screen")
    }

}

@Composable
fun Profile() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Text(text = "Profile Screen")
    }

}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route ){
        composable(BottomNavItem.Home.route){ HomeScreen(navController)}
        composable(BottomNavItem.Favourites.route){ Favourites()}
        composable(BottomNavItem.Profile.route){ Profile()}
    }

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar =  { BottomTabBar(navController = navController) }
    ){
        NavigationGraph(navController = navController)
    }

}

@Composable
fun BottomTabBar(navController: NavHostController) {
    val tabBarItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favourites,
        BottomNavItem.Profile
    )
    BottomAppBar {
        val navBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStack?.destination?.route

        tabBarItems.forEach { barItem ->
            val isSelected = currentRoute == barItem.route
            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(text = barItem.title)
                },
                onClick = {
                    navController.navigate(barItem.route)
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) barItem.selectedIcon else barItem.unselectedIcon,
                        contentDescription = barItem.title
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMain() {
    MainScreen()
}