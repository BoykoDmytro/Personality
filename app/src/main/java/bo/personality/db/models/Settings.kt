package bo.personality.db.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import bo.personality.db.dao.SettingsDao


/*
 * Created by Bo on 05.12.2018.
 */

@Entity(tableName = SettingsDao.TABLE_NAME)
data class Settings(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var target_id: Long? = null,
    var key: String? = null,
    var value: String? = null,
    var group: String? = null
)