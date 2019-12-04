package com.example.todo_list.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todo_list.DATE_FILENAME
import com.example.todo_list.DELIMITER
import com.example.todo_list.TASKS_FILENAME
import com.example.todo_list.util.FileOperations
import com.example.todo_list.util.TaskItem
import java.util.*

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

    // Find out if it is a new day (new day = all tasks set to incomplete)
    private fun isPastMidnight(): Boolean {
        // 1. Convert saved data to a Calendar object
        var saved_calendar = Calendar.getInstance()
        val saved_time = FileOperations.readDataFromFile(context, DATE_FILENAME)?.toLong() ?: Long.MAX_VALUE
        saved_calendar.timeInMillis = saved_time

        // 2. If right now is the next day, return true; else return false
        val right_now = Calendar.getInstance()
        return right_now.get(Calendar.DAY_OF_YEAR) > saved_calendar.get(Calendar.DAY_OF_YEAR)
    }

    fun deleteAllTasks() {
        val newArray: Array<TaskItem?> = arrayOfNulls<TaskItem>(0)
        taskItems.value = newArray
        saveTasks()
    }

    fun loadTasks() {
        val saved_tasks = FileOperations.readDataFromFile(context, TASKS_FILENAME)
        val set_completed_to_default = isPastMidnight()
        var is_completed = false

        if (saved_tasks != "") {
            val tasks = saved_tasks!!.split(DELIMITER)
            var index: Int
            for (index in tasks.indices step 2) {
                if (!set_completed_to_default) {
                    is_completed = tasks[index + 1].toBoolean()
                }
                addTask(tasks[index], is_completed)
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