package com.almogdadjabir.com.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chapter_table")
data class ChapterEntity(
    @PrimaryKey var id: Int?,
    var evelation_place: String?,
    val revelation_order: Int?,
    val bismillah_pre: String?,
    val name_simple: String?,
    val name_arabic: String?,
    val verses_count: Int?
)