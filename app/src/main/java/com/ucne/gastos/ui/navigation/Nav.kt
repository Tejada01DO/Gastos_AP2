package com.ucne.gastos.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ucne.gastos.ui.Consulta.C_Gastos
import com.ucne.gastos.ui.gastos.GastosScreen
import com.ucne.gastos.ui.home.Home

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Nav(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach{ navItems ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItems.route } == true,
                        onClick = {
                            navController.navigate(navItems.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                                  },
                        icon = {
                            Icon(
                                imageVector = navItems.icon,
                                contentDescription = null
                            )
                        }, label = {
                            Text(text = navItems.label)
                        })
                }
            }

        }
    ){paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.name,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable(route = Screens.Home.name){
                Home()
            }
            composable(route = Screens.GastosScreen.name){
                GastosScreen()
            }
            composable(route = Screens.C_Gastos.name){
                C_Gastos()
            }
        }
    }
}