package bo.personality.db.dao

import android.arch.persistence.room.*
import bo.personality.db.models.Settings
import io.reactivex.Flowable


/*
 * Created by Bo on 05.12.2018.
 */
@Dao
interface SettingsDao {
    companion object {
        const val TABLE_NAME = "settings"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSettings(settings: List<Settings>)

    @Query("SELECT  * from settings WHERE settings.target_id in (:entry_id)")
    fun getSettingsById(entry_id: Long) : Flowable<List<Settings>>

    @Query("SELECT  * from settings")
    fun geAllSettings() : Flowable<List<Settings>>

    @Update
    fun updateSettings(settings: List<Settings>)

    @Delete
    fun deleteSettings(settings: List<Settings>)
}