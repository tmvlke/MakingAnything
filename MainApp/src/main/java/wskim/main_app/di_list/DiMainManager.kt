package wskim.main_app.di_list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.corefeature.network.manager.NetworkCommonManager
import wskim.corefeature.preferences.SharedPreferencesManager
import wskim.main_app.repository.SearchResultRepository
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