package com.example.todo_list.util

import android.content.Context
import android.media.Image
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView

// Representation of a task
class TaskItem constructor(){

    private var task_text: String? = null
    private var completed: Boolean = false

    constructor(text: String): this() {
        task_text = text
    }

    // Context is passed in from MainActivity
    fun makeTask(context: Context): LinearLayout {
        val task_layout = LinearLayout(context)
        val textView = TextView(context)
        val checkBox = CheckBox(context)

        textView.text = task_text
        checkBox.isChecked = completed

        checkBox.setOnCheckedChangeListener{
            buttonView, isChecked -> setCompleted(checkBox.isChecked)
        }

        task_layout.addView(textView)
        task_layout.addView(checkBox)

        return task_layout
    }

    fun getCompleted(): Boolean {
        return completed
    }

    fun setCompleted(value: Boolean) {
        completed = value
    }

}