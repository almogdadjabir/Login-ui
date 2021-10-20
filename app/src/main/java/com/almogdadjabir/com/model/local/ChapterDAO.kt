package com.almogdadjabir.com.model.local

import androidx.room.*

@Dao
interface ChapterDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chapters: ChapterEntity)

    @Query("select * from chapter_table")
    suspend fun getAllLocalChapter(): List<ChapterEntity>
}