package my.dreamtech.trackstockapp.data.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "track_stock_table")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0L,
    @ColumnInfo(name = "company_name")
    val companyName: String = "",
    @ColumnInfo(name = "invested")
    val invested: String = "",
    @ColumnInfo(name = "profit")
    val profit: String = "",
    @ColumnInfo(name = "loss")
    val loss: String = "",
    @ColumnInfo(name = "date")
    val date:  String = ""
)
