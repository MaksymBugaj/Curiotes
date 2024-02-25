package com.bussar.curiosity.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bussar.curiosity.ui.navigation.MainNavigation
import com.bussar.curiosity.ui.theme.CuriosityTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuriosityTheme {
                MainNavigation()
            }
        }
    }
}
/**
 * plan for the app:
 * fix date to yyyy-MM-dd-hh:mm +
 * when creating a curiote, if an error is shown, then after filling any field error is dismantled=
 * add checkmark on item indicationg that this field is required to be extended
 * notyfikacje: custom do testów heathrow
 * filters and sorting
 * search bar
 * opening note on click (okno takie jak msg się otwiera okno w oknie, i tam przycisk edytuj)
 * notification:how many days it is not checked
 * kategorie:
 * colors to the category or item
 * time limits to the item
 * !!!!żeby zrobić dynamiczne(programatically) dodawanie linków, można zrobić lazy column,
 * !!!!nasłuchujące na listę, której rozmiar będzie się zmieniał w zależności od kliknięcia w +

 */