package com.bussar.curiosity.di

import android.content.Context
import androidx.room.Room
import com.bussar.curiosity.data.db.Database
import com.bussar.curiosity.data.db.dao.CuriousNoteDao
import com.bussar.curiosity.data.db.dao.CuriousNoteLinkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context = context,
            klass = Database::class.java,
            name = "db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideCuriousNoteDao(database: Database): CuriousNoteDao = database.curiousNoteDao()

    @Singleton
    @Provides
    fun provideCuriousNoteLinkDao(database: Database): CuriousNoteLinkDao = database.curiousNoteLinkDao()
}