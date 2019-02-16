package bo.personality.repository

import android.support.annotation.WorkerThread
import android.util.Log
import bo.personality.db.dao.TaskDao
import bo.personality.db.models.Task
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/*
 * Created by Bo on 11.12.2018.
 */
class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(): Flowable<List<Task>> {
        return taskDao.geAllTasks()
    }

    @WorkerThread
    fun saveTask(task: Task) {
        taskDao.addTask(task)
    }

    @WorkerThread
    fun saveTasks(task: List<Task>) {
        Completable.fromAction {
            taskDao.addTasks(task)
        }.subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
                Log.e(this::class.java.simpleName, "onSubscribe ")
            }

            override fun onComplete() {
                Log.e(this::class.java.simpleName, "onComplete ")
            }

            override fun onError(e: Throwable) {
                Log.e(this::class.java.simpleName, e.toString())
            }
        })
    }
}