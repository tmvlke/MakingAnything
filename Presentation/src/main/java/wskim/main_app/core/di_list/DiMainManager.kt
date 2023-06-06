package wskim.main_app.core.di_list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.data.network.manager.NetworkCommonManager
import wskim.data.preferences.SharedPreferencesManager
import wskim.data.repository.SearchResultRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiMainManager {

    @Singleton
    @Provides
    fun provideSearchResultRepository(
        networkCommonManager: NetworkCommonManager,
        sharedPreferencesManager: SharedPreferencesManager
    ) : SearchResultRepository {
        return SearchResultRepository(
            networkCommonManager,
            sharedPreferencesManager
        )
    }
}