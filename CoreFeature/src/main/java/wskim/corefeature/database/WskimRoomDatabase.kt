package wskim.corefeature.database

import androidx.room.Database
import androidx.room.RoomDatabase
import wskim.corefeature.database.dao.SearchKeywordDao
import wskim.corefeature.database.dao.ViewCountDao
import wskim.corefeature.database.table.SearchKeyword
import wskim.corefeature.database.table.ViewCount

@Database(entities = [ViewCount::class, SearchKeyword::class], version = 2)
abstract class WskimRoomDatabase : RoomDatabase() {
    abstract fun viewCountDao(): ViewCountDao
    abstract fun searchKeywordDao(): SearchKeywordDao
}