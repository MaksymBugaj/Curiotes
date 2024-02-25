package com.bussar.curiosity.ui.navigation

sealed class NavItem(val title: String, val screenRoute: String) {
    object Curiote : NavItem(title = "Curiote", screenRoute = NavRoute.curiotes)
    object CreateCuriote : NavItem(title = "Create", screenRoute = NavRoute.createCuriote)
    object EditCuriote : NavItem(title = "Edit", screenRoute = NavRoute.editCuriote)
}
