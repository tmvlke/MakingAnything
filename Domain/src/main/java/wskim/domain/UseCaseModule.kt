package wskim.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.domain.repository.LayoutListRepository
import wskim.domain.usecase.LayoutListUseCase
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideLayoutListUseCase(layoutListRepository: LayoutListRepository): LayoutListUseCase {
        return LayoutListUseCase(layoutListRepository)
    }
}