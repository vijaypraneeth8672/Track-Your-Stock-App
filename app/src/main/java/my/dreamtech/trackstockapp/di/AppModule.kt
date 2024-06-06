package my.dreamtech.trackstockapp.di

import android.app.Application
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.dreamtech.trackstockapp.data.local.StockDatabase
import my.dreamtech.trackstockapp.data.remote.StockApi
import my.dreamtech.trackstockapp.data.track.TrackDatabase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            "stockdb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackDatabase(app: Application): TrackDatabase{
        return Room.databaseBuilder(
            app,
            TrackDatabase::class.java,
            "trackdb.db"
        ).build()
    }

}