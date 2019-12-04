package com.example.todo_list

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CreditsActivity : AppCompatActivity() {

    private val github_link by lazy {
        findViewById<TextView>(R.id.github_link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credits_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        github_link.setOnClickListener {
            loadGithubLinkInBrowser()
        };
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CreditsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun loadGithubLinkInBrowser() {
        val url: String = "https://github.com/KaelenCarling/ToDo_List"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }
}
