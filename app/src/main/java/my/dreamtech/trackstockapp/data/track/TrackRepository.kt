package my.dreamtech.trackstockapp.data.track

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrackRepository @Inject constructor(
    private val db : TrackDatabase
) {

    private val trackDao = db.trackDao()

    suspend fun addStock(stock: TrackEntity){
        trackDao.addStock(stock)
    }

    fun getAllStocks() : Flow<List<TrackEntity>> = trackDao.getAllStocks()

    fun getStockById(id: Long): Flow<TrackEntity>{
        return  trackDao.getAStockById(id)
    }

    suspend fun updateStock(stock: TrackEntity){
        trackDao.updateStock(stock)
    }

    suspend fun deleteStock(stock: TrackEntity){
        trackDao.deleteStock(stock)
    }

}