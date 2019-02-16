package bo.personality.ui.adapters

import android.support.v7.widget.RecyclerView
import bo.personality.ui.viewholders.BaseVH


/*
 * Created by Bo on 10.12.2018.
 */
abstract class BaseAdapter<In, Vh : BaseVH<In>>(var mList: MutableList<In>?) : RecyclerView.Adapter<Vh>() {

    var items: MutableList<In>?
        get() = mList
        set(list) {
            mList = list
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.setData(getItem(position))
    }

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList!!.size
    }

    fun setItemsAndRefreshIfNeed(list: MutableList<In>, isNeedRefresh: Boolean) {
        mList = list
        if (isNeedRefresh) notifyDataSetChanged()
    }

    protected fun getItem(position: Int): In? {
        return if (position > -1 && position < mList!!.size) mList!![position] else null
    }

    fun addItem(message: In): Int {
        return addItem(-1, message)
    }

    fun addItem(position: Int, message: In): Int {
        checkList()
        if (position < 0) {
            mList!!.add(message)
            notifyItemInserted(mList!!.size - 1)
            return mList!!.size - 1
        } else {
            mList!!.add(position, message)
            notifyItemInserted(position)
            return position
        }
    }

    fun addItems(list: List<In>?) {
        checkList()
        if (list == null) return
        mList!!.addAll(list)
        notifyItemRangeInserted(mList!!.size - list.size, list.size)
    }

    fun insertItems(position: Int, list: List<In>) {
        checkList()
        mList!!.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
    }

    fun removeItems(itemList: List<In>) {
        if (!itemList.isEmpty()) {
            for (m in itemList) {
                removeItem(m)
            }
        }
    }

    fun removeItem(item: In) {
        val indexOf = mList!!.indexOf(item)
        if (indexOf > -1) {
            mList!!.remove(item)
            notifyItemRemoved(indexOf)
        }
    }

    protected fun checkList(): Boolean {
        if (mList == null) {
            mList = ArrayList()
            return true
        }
        return false
    }

    fun clearWithoutNotify() {
        if (mList != null) mList!!.clear()
    }

    fun clear() {
        if (mList != null) mList!!.clear()
        notifyDataSetChanged()
    }

    fun update(list: List<In>) {
        for (item in list) update(item)
    }

    fun update(`object`: In) {
        if (mList != null) {
            val i = mList!!.indexOf(`object`)
            if (i > -1) {
                mList!![i] = `object`
                notifyItemChanged(i)
            } else {
                mList!!.add(`object`)
                notifyItemInserted(mList!!.size - 1)
            }
        }

    }
}