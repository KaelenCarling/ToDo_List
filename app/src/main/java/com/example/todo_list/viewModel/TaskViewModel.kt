package com.example.todo_list.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo_list.util.TaskItem

class TaskViewModel(app: Application): AndroidViewModel(app) {

    val taskItems = MutableLiveData<Array<TaskItem?>?>()
    private val context = app

    init {
        taskItems.value = null
    }

    fun addTask() {
        taskItems.value
    }

    fun addTask(label: String) {
        if (label != "") {
            val taskItem = TaskItem(label)

            // Create a new array to add the item then reassign the taskItems member array
            val newArray: Array<TaskItem?> = arrayOfNulls<TaskItem>(taskItems.value?.size?.plus(1) ?: 1)
            for (index: Int in 0..newArray.size - 2) {
                newArray.set(index, taskItems.value!!.get(index))
            }
            newArray.set(newArray.size - 1, taskItem)
            taskItems.value = newArray
        }
    }

}