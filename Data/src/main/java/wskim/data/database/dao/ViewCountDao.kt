package wskim.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import wskim.data.database.table.ViewCount
import wskim.domain.proguard_safe_zone.vo.ViewCountResultVO
import wskim.domain.ui.UiRoot

@Dao
interface ViewCountDao {
    @Insert
    suspend fun insertOne(user: ViewCount)

    @Query("SELECT * FROM ViewCount")
    suspend fun selectAllData(): List<ViewCount>

    @Query("SELECT mainTabIndex as `index`, count(*) as count FROM ViewCount WHERE uiRoot = :uiRoot GROUP BY uiRoot, mainTabIndex")
    suspend fun selectSpecificTabViewCount(uiRoot: UiRoot.MainTab): List<ViewCountResultVO>?

    @Update
    suspend fun updateViewCount(user: ViewCount)

    @Delete
    suspend fun deleteViewCount(user: ViewCount)
}