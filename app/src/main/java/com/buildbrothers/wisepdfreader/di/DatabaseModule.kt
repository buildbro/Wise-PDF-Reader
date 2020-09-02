package com.buildbrothers.wisepdfreader.di

import android.content.Context
import androidx.room.Room
import com.buildbrothers.wisepdfreader.database.AppDatabase
import com.buildbrothers.wisepdfreader.database.DocumentDao
import com.buildbrothers.wisepdfreader.database.DocumentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "wise_pdf_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDocumentDao(db: AppDatabase): DocumentDao {
        return db.documentDao()
    }


}