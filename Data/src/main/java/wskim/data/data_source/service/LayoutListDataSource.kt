package wskim.data.data_source.service

import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.ui.MainTab

interface LayoutListDataSource {
    suspend fun selectDummyData(
        page: Int = 1,
        count: Int = 20
    ) : ArrayList<String>

    suspend fun selectSpecificTabViewCount(
        mainTab: MainTab
    ): List<ViewCountResultVO>?

    suspend fun insertViewCount(
        mainTab: MainTab,
        position: Int
    )
}