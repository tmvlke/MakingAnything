package wskim.domain.repository

import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.ui.MainTab

interface LayoutListRepository {

    suspend fun selectDummyData(
        page: Int,
        count: Int
    ) : ArrayList<String>

    suspend fun selectSpecificTabViewCount(
        mainTab: MainTab
    ): List<ViewCountResultVO>?

    suspend fun insertViewCount(
        mainTab: MainTab,
        position: Int
    )
}