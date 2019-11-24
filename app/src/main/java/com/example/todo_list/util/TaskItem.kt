package com.example.todo_list.util

import android.content.Context
import android.media.Image
import android.view.View
import android.widget.TextView

// Representation of a task
class TaskItem constructor(){

    var task_text: String? = null
    private var task_image: Image? = null
    private var completed: Boolean = false

    constructor(text: String): this() {
        task_text = text
    }

    // Context is passed in from MainActivity
    fun makeTask(context: Context): View {
        val view = TextView(context)
        view.text = task_text

        return view
    }

}