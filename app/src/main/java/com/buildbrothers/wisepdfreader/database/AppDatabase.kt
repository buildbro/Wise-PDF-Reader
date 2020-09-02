package com.buildbrothers.wisepdfreader.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.buildbrothers.wisepdfreader.model.Document

@Database(entities = [Document::class], version = 2, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun documentDao(): DocumentDao
}