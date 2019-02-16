package bo.personality.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.persistence.room.Room
import bo.personality.db.dao.TaskDao
import bo.personality.db.data.AppDataBase
import bo.personality.db.models.Task
import bo.personality.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


/*
 * Created by Bo on 10.12.2018.
 */
class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    init {
        val taskDao = AppDataBase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
    }

    fun getTasks(): Flowable<List<Task>> {
        return loadTasks()
    }

    private fun loadTasks(): Flowable<List<Task>> {
        return repository.getAllTasks()
    }

    fun saveTasks(tasks: List<Task>) {
        return repository.saveTasks(tasks)
    }

    fun saveTask(tasks: Task) {
        return repository.saveTask(tasks)
    }
}