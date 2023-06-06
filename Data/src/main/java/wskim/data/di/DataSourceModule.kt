package wskim.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.data.data_source.LayoutListDataSourceImpl
import wskim.data.data_source.service.LayoutListDataSource
import wskim.data.database.WskimRoomDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideLayoutListDataSource(roomDatabase: WskimRoomDatabase): LayoutListDataSource {
        return LayoutListDataSourceImpl(roomDatabase)
    }

}