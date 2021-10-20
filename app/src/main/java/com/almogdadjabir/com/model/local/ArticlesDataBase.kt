package com.almogdadjabir.com.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ArticlesEntity::class , ChapterEntity::class], version = 1)
@TypeConverters(ConvertersSource::class)
abstract class ArticlesDataBase : RoomDatabase() {

    abstract fun getDao(): ArticlesDAO

    abstract fun getQuranDao(): ChapterDAO

}