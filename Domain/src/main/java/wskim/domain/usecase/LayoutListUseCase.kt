package wskim.domain.usecase

import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.repository.LayoutListRepository
import wskim.domain.ui.UiRoot
import javax.inject.Inject

class LayoutListUseCase @Inject constructor(private val repository: LayoutListRepository){
    suspend fun selectSpecificTabViewCount(uiRoot: UiRoot.MainTab) : List<ViewCountResultVO>? = repository.selectSpecificTabViewCount(uiRoot)

    suspend fun insertViewCount(
        uiRoot: UiRoot.MainTab,
        position: Int
    ) = repository.insertViewCount(uiRoot, position)
}