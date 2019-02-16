package bo.personality.db.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import bo.personality.db.dao.LocationDao


/*
 * Created by Bo on 05.12.2018.
 */
@Entity(tableName = LocationDao.TABLE_NAME)
data class Location(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var target_id: Long? = null,
    var lat: Double? = null,
    var long: Double? = null,
    var address : String? = null
)