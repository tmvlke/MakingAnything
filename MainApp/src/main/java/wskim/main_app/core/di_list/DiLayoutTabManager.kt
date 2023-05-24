package wskim.main_app.core.di_list

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.corefeature.preferences.SharedPreferencesManager
import wskim.main_app.mvvm.repository.LayoutExampleInfinityScrollRepository
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