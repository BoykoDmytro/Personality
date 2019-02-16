package bo.personality.db.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import bo.personality.BuildConfig
import bo.personality.db.dao.LocationDao
import bo.personality.db.dao.SettingsDao
import bo.personality.db.dao.TaskDao
import bo.personality.db.models.Location
import bo.personality.db.models.Settings
import bo.personality.db.models.Task

/*
 * Created by Bo on 05.12.2018.
 */
@Database(entities = [Settings::class, Location::class, Task::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
    abstract fun locationDao(): LocationDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java, BuildConfig.APPLICATION_ID + ".db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}