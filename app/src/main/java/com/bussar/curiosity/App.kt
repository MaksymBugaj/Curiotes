package com.bussar.curiosity

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}

/**
 * quick explanation of the application
 * screen with curious notes:
 * list of items, sorting available in the next version
 * you can have the ability to search something by title or description
 * in note, you have title, description, links
 * when creating a note, one of the three fields has to be filled.
 * if you paste a link, then a field with a "+" will be shown to the user, indicating that another field could be added to the list
 *
 * bottom navigation in the next iteration
 *
 * first of all:
 * screen with creating an item
 * then screen displaying all the items
 * then google play store release
 * then notification
 * then widget
 * then bottom navigation
 * then screen displaying item
 */