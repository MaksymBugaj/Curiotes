package com.bussar.curiosity.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bussar.curiosity.ui.note.display.CuriousNotesView

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavItem.Curiote.screenRoute) {
        composable(route = NavItem.Curiote.screenRoute) {
            CuriousNotesView(viewModel = hiltViewModel())
        }
        composable(route = NavItem.EditCuriote.screenRoute) {
            //todo edit when new screen will be added
        }
    }
}