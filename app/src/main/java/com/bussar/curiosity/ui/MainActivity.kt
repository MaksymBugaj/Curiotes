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
