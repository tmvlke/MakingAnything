package wskim.corefeature.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SearchKeyword")
data class SearchKeyword(
    @PrimaryKey(autoGenerate = true)
    val index: Int = 0,
    val word: String,
)