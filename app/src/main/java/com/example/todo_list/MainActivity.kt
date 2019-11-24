package com.example.todo_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.todo_list.util.TaskItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

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
            task_list.addView(taskItem.makeTask(this))

            updateDisplay()
        }
    }

    // Update the display of each task item
    private fun updateDisplay() {
        //TODO
    }
}