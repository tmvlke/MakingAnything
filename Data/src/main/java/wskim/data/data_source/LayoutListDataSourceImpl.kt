package wskim.data.data_source

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import wskim.data.data_source.service.LayoutListDataSource
import wskim.data.database.WskimRoomDatabase
import wskim.data.database.table.ViewCount
import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.ui.UiRoot
import javax.inject.Inject

class LayoutListDataSourceImpl @Inject constructor(
    private val roomDatabase: WskimRoomDatabase
    ) : LayoutListDataSource {

    private fun makeDummyList(): ArrayList<String> {
        val dummyList = arrayListOf<String>()

        repeat(1000) {
            dummyList.add("${it}번 데이터")
        }
        return dummyList
    }

    override suspend fun selectDummyData(
        page: Int,
        count: Int
    ) : ArrayList<String> {
        val allData = makeDummyList()

        return allData.asFlow().drop((page-1) * count).take(count).toList() as ArrayList<String>
    }

    override suspend fun selectSpecificTabViewCount(
        uiRoot: UiRoot.MainTab
    ): List<ViewCountResultVO>? {
        roomDatabase.viewCountDao().selectAllData()
        return roomDatabase.viewCountDao().selectSpecificTabViewCount(uiRoot)
    }

    override suspend fun insertViewCount(
        uiRoot: UiRoot.MainTab,
        position: Int
    ) {
        roomDatabase.viewCountDao().insertOne(
            ViewCount(
                uiRoot = uiRoot,
                mainTabIndex = position,
            )
        )
    }
}