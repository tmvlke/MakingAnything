package wskim.main_app.core.di_list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.data.preferences.SharedPreferencesManager
import wskim.data.repository.LayoutExampleInfinityScrollRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiLayoutTabManager {

    @Singleton
    @Provides
    fun provideLayoutExampleInfinityScrollRepository(
        sharedPreferencesManager: SharedPreferencesManager
    ) : LayoutExampleInfinityScrollRepository {
        return LayoutExampleInfinityScrollRepository(
            sharedPreferencesManager
        )
    }
}