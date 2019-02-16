package bo.personality.ui.listeners


/*
 * Created by Bo on 10.12.2018.
 */
interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}