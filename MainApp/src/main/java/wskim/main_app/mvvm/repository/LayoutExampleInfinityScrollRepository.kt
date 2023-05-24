package wskim.main_app.mvvm.repository

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import wskim.corefeature.preferences.SharedPreferencesManager

class LayoutExampleInfinityScrollRepository(
    private val sharedPreferencesManager: SharedPreferencesManager
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
}