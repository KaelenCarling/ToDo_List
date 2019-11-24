package com.example.todo_list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoadDeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_delete)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoadDeleteActivity::class.java)
            context.startActivity(intent)
        }
    }
}
