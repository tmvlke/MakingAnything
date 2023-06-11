package wskim.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.domain.repository.LayoutExampleInfinityScrollRepository
import wskim.domain.repository.LayoutListRepository
import wskim.domain.repository.SearchResultRepository
import wskim.domain.usecase.LayoutExampleInfinityScrollUseCase
import wskim.domain.usecase.LayoutListUseCase
import wskim.domain.usecase.SearchResultUseCase
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideLayoutExampleInfinityScrollUseCase(layoutExampleInfinityScrollRepository: LayoutExampleInfinityScrollRepository): LayoutExampleInfinityScrollUseCase {
        return LayoutExampleInfinityScrollUseCase(layoutExampleInfinityScrollRepository)
    }

    @Provides
    @Singleton
    fun provideLayoutListUseCase(layoutListRepository: LayoutListRepository): LayoutListUseCase {
        return LayoutListUseCase(layoutListRepository)
    }

    @Provides
    @Singleton
    fun provideSearchResultUseCase(searchResultRepository: SearchResultRepository): SearchResultUseCase {
        return SearchResultUseCase(searchResultRepository)
    }
}