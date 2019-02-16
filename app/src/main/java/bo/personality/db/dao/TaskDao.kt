package bo.personality.db.dao

import android.arch.persistence.room.*
import bo.personality.db.models.Task
import io.reactivex.Completable
import io.reactivex.Flowable


/*
 * Created by Bo on 05.12.2018.
 */
@Dao
interface TaskDao {
    companion object {
        const val TABLE_NAME = "task"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTasks(task: List<Task>)

    @Query("SELECT  * from task WHERE id = (:entry_id)")
    fun getTaskById(entry_id: Long): Flowable<List<Task>>

    @Query("SELECT  * from task")
    fun geAllTasks(): Flowable<List<Task>>

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteSettings(task: Task)
}