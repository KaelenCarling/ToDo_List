package com.example.todo_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todo_list.util.FileOperations
import com.example.todo_list.util.TaskItem
import com.example.todo_list.viewModel.TaskViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this)
            .get(TaskViewModel::class.java)
        viewModel.taskItems.observe(this, Observer {
            updateDisplay(it)
        })

        // Quick add button listener
        quick_add_button.setOnClickListener { quickAddButtonHandler() }

        updateDisplay(null)
    }

    override fun onPause() {
        super.onPause()

        // Save the tasks to the file system
        viewModel.saveTasks()

        // Save the date before the app is closed
        FileOperations.saveDataToFile(this.application, Calendar.getInstance().timeInMillis.toString(), DATE_FILENAME)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save_list) {
            viewModel.saveTasks()
            return true
        }
        if (item.itemId == R.id.action_delete_list) {
            viewModel.deleteAllTasks()
            return true
        }
        if (item.itemId == R.id.action_credits) {
            CreditsActivity.start(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Quick add button handler
    private fun quickAddButtonHandler() {
        val task_text = task_text_input.text.toString()
        if (task_text != "") {
            viewModel.addTask(task_text)
            task_text_input.text!!.clear()
            task_text_input.clearFocus()
        }
    }

    // Update the display of each task item
    private fun updateDisplay(taskItems: Array<TaskItem?>?) {
        // Remove any current views and replace with new ones
        task_list.removeAllViews()

        // Replace each task item in task_list with the updated content stored in the array
        if (taskItems != null) {
            for (index: Int in 0 until taskItems.size) {
                task_list.addView(taskItems.get(index)!!.makeTask(this))
            }
        }
    }
}