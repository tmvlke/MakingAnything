package wskim.domain.usecase

import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.repository.LayoutListRepository
import wskim.domain.ui.MainTab
import javax.inject.Inject

class LayoutListUseCase @Inject constructor(private val repository: LayoutListRepository){
    suspend fun selectSpecificTabViewCount(mainTab: MainTab) : List<ViewCountResultVO>? = repository.selectSpecificTabViewCount(mainTab)

    suspend fun insertViewCount(
        mainTab: MainTab,
        position: Int
    ) = repository.insertViewCount(mainTab, position)
}