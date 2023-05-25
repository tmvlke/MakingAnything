package wskim.corefeature.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import wskim.corefeature.database.table.SearchKeyword


@Dao
interface SearchKeywordDao {
    @Insert
    suspend fun insert(searchKeyword: SearchKeyword)

    @Query("SELECT * FROM SearchKeyword")
    suspend fun getAllUsers(): List<SearchKeyword>

    @Query("SELECT * FROM SearchKeyword WHERE `index` = :index")
    suspend fun getUserById(index: Long): SearchKeyword?

    @Update
    suspend fun updateUser(searchKeyword: SearchKeyword)

    @Delete
    suspend fun deleteUser(searchKeyword: SearchKeyword)
}