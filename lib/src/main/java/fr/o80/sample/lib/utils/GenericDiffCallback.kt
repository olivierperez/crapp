package fr.o80.sample.lib.utils

/**
 * @author Olivier Perez
 */
import android.support.v7.util.DiffUtil

abstract class GenericDiffCallback<T>(protected val oldList: List<T>, protected val newList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return isSameItem(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
            = oldList[oldItemPosition] == newList[newItemPosition]

    protected abstract fun isSameItem(oldItem: T, newItem: T): Boolean

}