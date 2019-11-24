package com.example.todo_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.todo_list.util.TaskItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var taskItems: Array<TaskItem?>? = (null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Quick add button listener
        quick_add_button.setOnClickListener { quickAddButtonHandler() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Quick add button handler
    private fun quickAddButtonHandler() {
        val task_text = task_text_input.text.toString()
        if (task_text != "") addTask(task_text)
    }

    // Add a task
    private fun addTask(label: String) {
        if (label != "") {
            val taskItem = TaskItem(label)

            // Create a new array to add the item then reassign the taskItems member array
            val newArray: Array<TaskItem?> = arrayOfNulls<TaskItem>(taskItems?.size?.plus(1) ?: 1)
            for (index: Int in 0..newArray.size - 2) {
                newArray.set(index, taskItems!!.get(index))
            }
            newArray.set(newArray.size - 1, taskItem)
            taskItems = newArray

            updateDisplay()
        }
    }

    // Update the display of each task item
    private fun updateDisplay() {
        // Remove any current views and replace with new ones
        task_list.removeAllViews()

        // Replace each task item in task_list with the updated content stored in the array
        if (taskItems != null) {
            for (index: Int in 0..taskItems!!.size - 1) {
                task_list.addView(taskItems!!.get(index)!!.makeTask(this))
            }
        }
    }
}