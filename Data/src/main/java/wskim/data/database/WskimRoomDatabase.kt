package wskim.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import wskim.data.database.dao.SearchKeywordDao
import wskim.data.database.dao.ViewCountDao
import wskim.data.database.table.SearchKeyword
import wskim.data.database.table.ViewCount

@Database(entities = [ViewCount::class, SearchKeyword::class], version = 2)
abstract class WskimRoomDatabase : RoomDatabase() {
    abstract fun viewCountDao(): ViewCountDao
    abstract fun searchKeywordDao(): SearchKeywordDao
}