package bo.personality.ui.viewholders

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bo.personality.utils.DateUtils


/*
 * Created by Bo on 10.12.2018.
 */
abstract class BaseVH<I>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val context: Context = itemView.context

    constructor(parent: ViewGroup, @LayoutRes layoutId: Int) : this(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    )

    abstract fun setData(item: I?)

    fun onClick() {}

    protected fun getTime(time: Long?): String? {
        return if (time == null) null else DateUtils.getHourDate(time)
    }
}