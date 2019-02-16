package bo.personality.ui.adapters

import android.view.ViewGroup
import bo.personality.db.models.Task
import bo.personality.ui.listeners.OnItemClickListener
import bo.personality.ui.viewholders.TaskVH


/*
 * Created by Bo on 10.12.2018.
 */
class TaskRVAdapter(tasks: MutableList<Task>) : BaseAdapter<Task, TaskVH>(tasks), OnItemClickListener<Task> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        return  TaskVH(parent, this)
    }

    override fun onItemClick(item: Task, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}