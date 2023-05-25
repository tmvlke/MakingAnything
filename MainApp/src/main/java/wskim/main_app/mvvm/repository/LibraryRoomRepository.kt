package wskim.main_app.mvvm.repository

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import wskim.baselibrary.ui.MainTab
import wskim.corefeature.database.WskimRoomDatabase
import wskim.corefeature.database.table.ViewCount
import wskim.corefeature.database.vo.ViewCountResultVO

class LibraryRoomRepository(
    private val roomDatabase: WskimRoomDatabase
    ) {

    private fun makeDummyList(): ArrayList<String> {
        val dummyList = arrayListOf<String>()

        repeat(1000) {
            dummyList.add("${it}번 데이터")
        }
        return dummyList
    }

    suspend fun selectDummyData(
        page: Int = 1,
        count: Int = 20
    ) : ArrayList<String> {
        val allData = makeDummyList()

        return allData.asFlow().drop((page-1) * count).take(count).toList() as ArrayList<String>
    }

    suspend fun selectSpecificTabViewCount(
        mainTab: MainTab
    ): List<ViewCountResultVO>? {
        roomDatabase.viewCountDao().selectAllData()
        return roomDatabase.viewCountDao().selectSpecificTabViewCount(mainTab)
    }

    suspend fun insertViewCount(
        mainTab: MainTab,
        position: Int
    ) {
        roomDatabase.viewCountDao().insertOne(
            ViewCount(
                mainTab = mainTab,
                mainTabIndex = position,
            )
        )
    }
}