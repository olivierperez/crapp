package fr.o80.sample.lib.utils

/**
 * @author Olivier Perez
 */
import android.support.v7.util.DiffUtil

abstract class GenericDiffCallback<in T>(private val oldList: List<T>, private val newList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            isSameItem(oldList[oldItemPosition], newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    protected abstract fun isSameItem(oldItem: T, newItem: T): Boolean

}