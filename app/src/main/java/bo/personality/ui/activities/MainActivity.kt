package bo.personality.ui.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import bo.personality.db.models.Task
import bo.personality.viewmodels.TaskViewModel
import bo.personality.R
import bo.personality.db.models.Location
import bo.personality.ui.adapters.TaskRVAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.UUID
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskRVAdapter: TaskRVAdapter
    private var mutableList: MutableList<Task> = arrayListOf()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskRVAdapter = TaskRVAdapter(mutableList)
        main_rv.adapter = taskRVAdapter

        taskViewModel = ViewModelProviders.of(this@MainActivity).get(TaskViewModel::class.java)
        taskViewModel.getTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data2 ->

                if (data2 == null || data2.isEmpty()) {
                    for (i in 1..10) {
                        val timeMillis = System.currentTimeMillis()
                        mutableList.add(
                            Task(
                                i.toLong(),
                                "Title $i",
                                UUID.randomUUID().toString(),
                                timeMillis + Random.nextInt(2000, 20000),
                                timeMillis + Random.nextInt(4000, 300000),
                                arrayListOf(
                                    Location(
                                        (i * i).toLong(),
                                        Random.nextLong(),
                                        Random.nextDouble(),
                                        Random.nextDouble(),
                                        UUID.randomUUID().toString()
                                    )
                                ),
                                listOf()
                            )
                        )
                    }
                    taskViewModel.saveTasks(mutableList)
                }
                initData(data2)
            }
    }

    private fun initData(data: List<Task>?) {
        taskRVAdapter.addItems(data)
    }
}
