package com.bussar.curiosity.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bussar.curiosity.data.db.converter.ZonedDateTimeConverter
import com.bussar.curiosity.data.db.dao.CuriousNoteDao
import com.bussar.curiosity.data.db.dao.CuriousNoteLinkDao
import com.bussar.curiosity.data.db.entity.CuriousNoteEntity
import com.bussar.curiosity.data.db.entity.CuriousNoteLinkEntity

@Database(
    entities = [
        CuriousNoteEntity::class,
        CuriousNoteLinkEntity::class
    ],
    version = RoomDatabaseHelper.roomDatabaseVersion
)
@TypeConverters(
    ZonedDateTimeConverter::class
)
abstract class Database : RoomDatabase() {
    abstract fun curiousNoteDao(): CuriousNoteDao
    abstract fun curiousNoteLinkDao(): CuriousNoteLinkDao
}