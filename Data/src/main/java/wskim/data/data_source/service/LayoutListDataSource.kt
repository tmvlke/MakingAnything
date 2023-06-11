package wskim.data.data_source.service

import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.ui.UiRoot

interface LayoutListDataSource {
    suspend fun selectDummyData(
        page: Int = 1,
        count: Int = 20
    ) : ArrayList<String>

    suspend fun selectSpecificTabViewCount(
        uiRoot: UiRoot.MainTab
    ): List<ViewCountResultVO>?

    suspend fun insertViewCount(
        uiRoot: UiRoot.MainTab,
        position: Int
    )
}