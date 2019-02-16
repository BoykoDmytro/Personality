package bo.personality.db.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation
import bo.personality.db.dao.TaskDao

/*
 * Created by Bo on 05.12.2018.
 */
@Entity(tableName = TaskDao.TABLE_NAME)
data class Task(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var timestart: Long? = null,
    var timeend: Long? = null,
    @Ignore var location: List<Location>? = null,
    @Ignore var settings: List<Settings>? = null
)


//{
//    "id": 1,
//    "description": "asdasdas",
//    "title": "title",
//    "timestart": 12323232,
//    "timeend": 1111111,
//    "location": {
//    "lat": 112,
//    "lng": 122
//},
//    "settings": [
//    {
//        "id": "featureId",
//        "key": "key",
//        "value": "value",
//        "group": "group"
//    }
//    ]
//}