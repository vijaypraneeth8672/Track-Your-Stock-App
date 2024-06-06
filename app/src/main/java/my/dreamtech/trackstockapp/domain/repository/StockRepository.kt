package my.dreamtech.trackstockapp.domain.repository


import kotlinx.coroutines.flow.Flow
import my.dreamtech.trackstockapp.domain.model.CompanyInfo
import my.dreamtech.trackstockapp.domain.model.CompanyListingModel
import my.dreamtech.trackstockapp.domain.model.IntradayInfo
import my.dreamtech.trackstockapp.util.Resource

interface StockRepository{

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query:String
    ):Flow<Resource<List<CompanyListingModel>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}