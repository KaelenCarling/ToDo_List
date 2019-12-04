package com.example.todo_list.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo_list.DELIMITER
import com.example.todo_list.TASKS_FILENAME
import com.example.todo_list.util.FileOperations
import com.example.todo_list.util.TaskItem

class TaskViewModel(app: Application): AndroidViewModel(app) {

    val taskItems = MutableLiveData<Array<TaskItem?>?>()
    private val context = app

    init {
        taskItems.value = null
        loadTasks()
    }

    fun addTask() {
        taskItems.value
    }

    fun addTask(label: String, completed: Boolean = false) {
        if (label != "") {
            val taskItem = TaskItem(label, completed)

            // Create a new array to add the item then reassign the taskItems member array
            val newArray: Array<TaskItem?> = arrayOfNulls<TaskItem>(taskItems.value?.size?.plus(1) ?: 1)

            for (index: Int in 0..newArray.size - 2) {
                newArray.set(index, taskItems.value!!.get(index))
            }

            newArray.set(newArray.size - 1, taskItem)
            taskItems.value = newArray
        }
    }

    fun loadTasks() {
        val saved_tasks = FileOperations.readDataFromFile(context, TASKS_FILENAME)

        if (saved_tasks != "") {
            val tasks = saved_tasks!!.split(DELIMITER)
            var index: Int
            for (index in tasks.indices step 2) {
                addTask(tasks[index], tasks[index + 1].toBoolean())
            }
        }
    }

    fun saveTasks() {
        var output_text = ""

        for (task in taskItems.value ?: emptyArray()) {
            output_text += task!!.getTaskText()
            output_text += DELIMITER
            output_text += task.getCompleted()
            output_text += DELIMITER
        }

        if (output_text != "") {
            output_text = output_text.substring(0, output_text.length - 1)
        }

        FileOperations.saveDataToFile(context, output_text, TASKS_FILENAME)
    }

}