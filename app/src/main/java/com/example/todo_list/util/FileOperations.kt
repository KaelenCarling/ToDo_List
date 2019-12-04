package com.example.todo_list.util

import android.app.Application
import java.io.File

class FileOperations {

    companion object {

        // Save the date to a file
        fun saveDataToFile(app: Application, data_to_save: String, filename: String) {
            val file = File(app.filesDir, filename)
            file.writeText(data_to_save ?: "", Charsets.UTF_8)
        }

        // Read the saved date from the file
        fun readDataFromFile(app: Application, filename: String): String? {
            val file = File(app.filesDir, filename)
            if (file.exists()) {
                return file.readText()
            }
            else {
                return null
            }
        }

    }

}