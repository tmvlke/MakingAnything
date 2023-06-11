package wskim.domain.repository

import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.ui.UiRoot

interface LayoutListRepository {

    suspend fun selectDummyData(
        page: Int,
        count: Int
    ) : ArrayList<String>

    suspend fun selectSpecificTabViewCount(
        uiRoot: UiRoot.MainTab
    ): List<ViewCountResultVO>?

    suspend fun insertViewCount(
        uiRoot: UiRoot.MainTab,
        position: Int
    )
}