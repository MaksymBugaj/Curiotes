package com.bussar.curiosity.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bussar.curiosity.ui.note.create.CreateCuriousNote
import com.bussar.curiosity.ui.note.display.CuriousNotesView
import com.bussar.curiosity.ui.note.edit.EditCuriousNote

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavItem.Curiote.screenRoute) {
        composable(route = NavItem.Curiote.screenRoute) {
            CuriousNotesView(
                viewModel = hiltViewModel(),
                onCreateCurioteClick = {
                    navController.navigate(NavItem.CreateCuriote.screenRoute)
                },
                onCurioteClick = { curioteId ->
                    navController.navigate("${NavItem.EditCuriote.screenRoute}/$curioteId")
                }
            )
        }
        composable(route = "${NavItem.EditCuriote.screenRoute}/{curioteId}",
            arguments = listOf(
                navArgument(name = "curioteId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
            ) { navBackStackEntry ->
            val curioteId = navBackStackEntry.arguments?.getLong("curioteId")
            EditCuriousNote(viewModel = hiltViewModel(), onSaved = {
                navController.popBackStack()
            },
                curioteId = curioteId)
        }
        composable(route = NavItem.CreateCuriote.screenRoute) {
            CreateCuriousNote(viewModel = hiltViewModel(), onSaved = {
                navController.popBackStack()
            })
        }
    }
}