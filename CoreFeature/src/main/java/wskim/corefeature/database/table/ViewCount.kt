package wskim.corefeature.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import wskim.baselibrary.ui.MainTab
import java.util.Date

@Entity(tableName = "ViewCount")
data class ViewCount (
    @PrimaryKey(autoGenerate = true)
    val viewCountNo: Int = 0,
    val mainTab: MainTab,
    val mainTabIndex: Int = 0,
    val regDT: Long = System.currentTimeMillis()
)