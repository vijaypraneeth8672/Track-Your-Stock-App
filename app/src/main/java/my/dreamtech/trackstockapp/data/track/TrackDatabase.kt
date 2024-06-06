package my.dreamtech.trackstockapp.data.track

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [TrackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TrackDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}