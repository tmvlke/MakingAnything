package wskim.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.data.data_source.service.LayoutListDataSource
import wskim.data.network.manager.NetworkCommonManager
import wskim.data.preferences.SharedPreferencesManager
import wskim.data.repository.LayoutExampleInfinityScrollRepositoryImpl
import wskim.data.repository.LayoutListRepositoryImpl
import wskim.data.repository.SearchResultRepositoryImpl
import wskim.domain.repository.LayoutExampleInfinityScrollRepository
import wskim.domain.repository.LayoutListRepository
import wskim.domain.repository.SearchResultRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLayoutExampleInfinityScrollRepository(sharedPreferencesManager: SharedPreferencesManager): LayoutExampleInfinityScrollRepository {
        return LayoutExampleInfinityScrollRepositoryImpl(sharedPreferencesManager)
    }

    @Provides
    @Singleton
    fun provideLayoutListRepository(layoutListDataSource: LayoutListDataSource): LayoutListRepository {
        return LayoutListRepositoryImpl(layoutListDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchResultRepository(
        networkCommonManager: NetworkCommonManager,
        sharedPreferencesManager: SharedPreferencesManager
    ): SearchResultRepository {
        return SearchResultRepositoryImpl(
            networkCommonManager,
            sharedPreferencesManager
        )
    }
}