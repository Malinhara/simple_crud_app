package com.example.kotlinapp1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Viewdata : AppCompatActivity() {

    private lateinit var dbHandler: Dbhandler
    private lateinit var listView: ListView
    private val dataList = mutableListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewdata)

        listView = findViewById(R.id.Viewdata)
        dbHandler = Dbhandler(this)

        // Retrieve data from the database
        val dataFromDb = dbHandler.viewData()
        dataList.addAll(dataFromDb)

        // Create an ArrayAdapter to display the data in the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList.map { "${it.getName()}\n${it.getDeec()}" })

        // Set the adapter to the ListView
        listView.adapter = adapter

        // Set item click listener
        listView.setOnItemClickListener { _, _, position, _ ->
            // Pass the data (including ID) to another activity for editing
            val selectedModel = dataList[position]
            val intent = Intent(this@Viewdata, updatedata::class.java).apply {
                putExtra("ID", selectedModel.getId())
                putExtra("NAME", selectedModel.getName())
                putExtra("DESCRIPTION", selectedModel.getDeec())
            }
            startActivity(intent)
        }
    }
}
