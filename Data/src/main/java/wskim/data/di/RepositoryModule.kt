package wskim.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.data.data_source.service.LayoutListDataSource
import wskim.data.repository.LayoutListRepositoryImpl
import wskim.domain.repository.LayoutListRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLayoutListRepository(layoutListDataSource: LayoutListDataSource): LayoutListRepository {
        return LayoutListRepositoryImpl(layoutListDataSource)
    }
}