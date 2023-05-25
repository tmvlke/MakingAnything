package wskim.main_app.core.di_list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.corefeature.database.WskimRoomDatabase
import wskim.main_app.mvvm.repository.LibraryRoomRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiLibraryTabManager {

    @Singleton
    @Provides
    fun provideLibraryRoomRepository(
        roomDatabase: WskimRoomDatabase
    ) : LibraryRoomRepository {
        return LibraryRoomRepository(
            roomDatabase
        )
    }
}