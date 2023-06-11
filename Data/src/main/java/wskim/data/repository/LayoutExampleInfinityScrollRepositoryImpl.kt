package wskim.data.repository

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import wskim.data.preferences.SharedPreferencesManager
import wskim.domain.repository.LayoutExampleInfinityScrollRepository

class LayoutExampleInfinityScrollRepositoryImpl(
    private val sharedPreferencesManager: SharedPreferencesManager
) : LayoutExampleInfinityScrollRepository {

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
    ): ArrayList<String> {
        val allData = makeDummyList()

        return allData.asFlow().drop((page - 1) * count).take(count).toList() as ArrayList<String>
    }
}