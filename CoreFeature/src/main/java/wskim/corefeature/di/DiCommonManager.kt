package wskim.corefeature.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import wskim.corefeature.database.WskimRoomDatabase
import wskim.corefeature.preferences.SharedPreferencesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiCommonManager {

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context
    ) : SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Singleton
    @Provides
    fun provideWskimRoomDatabase(
        @ApplicationContext context: Context
    ) : WskimRoomDatabase {
        return Room.databaseBuilder(
            context,
            WskimRoomDatabase::class.java,
            "provideWskimRoomDatabase"
        ).fallbackToDestructiveMigration() // 자동 마이그레이션
            .build()
    }
}