package wskim.domain.usecase

import wskim.domain.repository.LayoutExampleInfinityScrollRepository
import javax.inject.Inject

class LayoutExampleInfinityScrollUseCase @Inject constructor(private val repository: LayoutExampleInfinityScrollRepository){

    suspend fun selectDummyData(
        page: Int = 1,
        count: Int = 20
    ): ArrayList<String> = repository.selectDummyData(page, count)
}