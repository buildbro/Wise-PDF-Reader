package com.buildbrothers.wisepdfreader.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "my_pdf_docs", indices = [Index(value = ["filePath"], unique = true)])
data class Document(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "filePath") val filePath: String,
    @ColumnInfo(name = "lastRead") val lastRead: Long
    )