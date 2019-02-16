package bo.personality.db.dao

import android.arch.persistence.room.*
import bo.personality.db.models.Location
import io.reactivex.Flowable


/*
 * Created by Bo on 05.12.2018.
 */
@Dao
interface LocationDao {
    companion object {
        const val TABLE_NAME = "location"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLocations(settings: List<Location>)

    @Query("SELECT  * from location WHERE target_id in (:entry_id)")
    fun getLocationsById(entry_id: Long) : Flowable<List<Location>>

    @Query("SELECT  * from location")
    fun getAllLocations() : Flowable<List<Location>>

    @Update
    fun updateLocation(location: List<Location>)

    @Delete
    fun deleteLocation(location: List<Location>)
}