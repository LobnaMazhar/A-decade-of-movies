package lobna.swvl.adecadeofmovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import lobna.swvl.adecadeofmovies.data.MovieModel
import lobna.swvl.adecadeofmovies.database.dao.MovieDao

@Database(entities = [MovieModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: MyRoomDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: getInstance(context).also { instance = it }
        }

        private fun getInstance(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, MyRoomDatabase::class.java, "movies"
            ).build()

    }

    abstract fun movieDao(): MovieDao
}