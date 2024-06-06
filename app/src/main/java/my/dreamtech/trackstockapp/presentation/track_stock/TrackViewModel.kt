package my.dreamtech.trackstockapp.presentation.track_stock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import my.dreamtech.trackstockapp.data.track.TrackEntity
import my.dreamtech.trackstockapp.data.track.TrackRepository
import javax.inject.Inject


@HiltViewModel
class TrackViewModel @Inject constructor(
    private val repository: TrackRepository
) : ViewModel(){

    var stockCompanyNameState by mutableStateOf("")
    var stockInvestedState by mutableStateOf("")
    var stockProfitState by mutableStateOf("")
    var stockLossState by mutableStateOf("")
    var stockDateState by mutableStateOf("")


    fun onStockCompanyNameChanged(newString: String){
        stockCompanyNameState = newString
    }

    fun onStockInvestedChanged(newString: String){
        stockInvestedState = newString
    }

    fun onStockProfitChanged(newString: String){
        stockProfitState = newString
    }

    fun onStockLossChanged(newString: String){
        stockLossState = newString
    }

    fun onStockDateChanged(newString: String){
        stockDateState = newString
    }

    lateinit var getAllStocks : Flow<List<TrackEntity>>

    init {
        viewModelScope.launch {
            getAllStocks = repository.getAllStocks()
        }
    }

    fun addStock(stock: TrackEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStock(stock)
        }
    }

    fun getAStockById(id:Long): Flow<TrackEntity>{
        return repository.getStockById(id)
    }

    fun updateStock(stock: TrackEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateStock(stock)
        }
    }

    fun deleteStock(stock: TrackEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStock(stock)
            getAllStocks = repository.getAllStocks()
        }
    }


}