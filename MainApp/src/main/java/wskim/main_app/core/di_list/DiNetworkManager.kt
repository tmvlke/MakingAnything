package wskim.main_app.core.di_list

import system.config.BuildConfig
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import wskim.corefeature.network.manager.NetworkCommonManager
import wskim.corefeature.network.retrofit.RetrofitClient
import wskim.corefeature.network.retrofit.ServerAPI
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiNetworkManager {

    @Singleton
    @Provides
    fun provideServerAPI() : ServerAPI {
        return RetrofitClient(BuildConfig().serverUrl).restService
    }

    @Singleton
    @Provides
    fun provideNetworkCommonManager(
        @ApplicationContext context: Context,
        provideServerAPI : ServerAPI,
    ) : NetworkCommonManager {
        return NetworkCommonManager(
            context,
            provideServerAPI,
        )
    }
}