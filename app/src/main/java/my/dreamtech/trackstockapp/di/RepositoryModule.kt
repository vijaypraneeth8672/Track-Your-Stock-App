package my.dreamtech.trackstockapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.dreamtech.trackstockapp.data.csv.CSVParser
import my.dreamtech.trackstockapp.data.csv.CompanyListingsParser
import my.dreamtech.trackstockapp.data.csv.IntradayInfoParser
import my.dreamtech.trackstockapp.data.repository.StockRepositoryImpl
import my.dreamtech.trackstockapp.data.track.TrackRepository
import my.dreamtech.trackstockapp.domain.model.CompanyListingModel
import my.dreamtech.trackstockapp.domain.model.IntradayInfo
import my.dreamtech.trackstockapp.domain.repository.StockRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListingModel>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

}