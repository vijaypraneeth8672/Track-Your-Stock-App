package my.dreamtech.trackstockapp.data.track

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStock(stock: TrackEntity)

    @Query("Select * From 'track_stock_table'")
    fun getAllStocks() : Flow<List<TrackEntity>>

    @Update
    suspend fun updateStock(stock: TrackEntity)

    @Delete
    suspend fun deleteStock(stock: TrackEntity)

    @Query("Select * From 'track_stock_table' where id =:id")
    fun getAStockById(id: Long): Flow<TrackEntity>



}