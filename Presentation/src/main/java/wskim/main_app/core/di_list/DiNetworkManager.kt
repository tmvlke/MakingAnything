package wskim.main_app.core.di_list

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import system.config.BuildConfig
import wskim.data.network.manager.NetworkCommonManager
import wskim.data.network.retrofit.RetrofitClient
import wskim.data.network.retrofit.ServerAPI
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