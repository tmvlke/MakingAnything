package wskim.data.repository

import wskim.data.data_source.service.LayoutListDataSource
import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.repository.LayoutListRepository
import wskim.domain.ui.UiRoot
import javax.inject.Inject

class LayoutListRepositoryImpl @Inject constructor(
    private val layoutListDataSource: LayoutListDataSource
    ) : LayoutListRepository {

    override suspend fun selectDummyData(
        page: Int,
        count: Int
    ) : ArrayList<String> {
        return layoutListDataSource.selectDummyData(page, count)
    }

    override suspend fun selectSpecificTabViewCount(
        uiRoot: UiRoot.MainTab
    ): List<ViewCountResultVO>? {
        return layoutListDataSource.selectSpecificTabViewCount(uiRoot)
    }

    override suspend fun insertViewCount(
        uiRoot: UiRoot.MainTab,
        position: Int
    ) {
        layoutListDataSource.insertViewCount(uiRoot, position)
    }
}