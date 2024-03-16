package com.example.kotlinapp1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class updatedata : AppCompatActivity() {

    private lateinit var dbHandler: Dbhandler

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatedata)

        val nameEditText = findViewById<EditText>(R.id.editTextText)
        val descriptionEditText = findViewById<EditText>(R.id.editTextText2)
        val saveButton = findViewById<Button>(R.id.button3)
        val Deletebutton=findViewById<Button>(R.id.button4)

        // Retrieve the data passed from the previous activity
        val id = intent.getIntExtra("ID", 0)
        val name = intent.getStringExtra("NAME")
        val description = intent.getStringExtra("DESCRIPTION")

        // Set the retrieved values to the EditText fields
        nameEditText.setText(name)
        descriptionEditText.setText(description)

        dbHandler = Dbhandler(this)

        saveButton.setOnClickListener {

            val model = Model()
            model.setName(nameEditText.text.toString()) // Use text property to get the content
            model.setDeec(descriptionEditText.text.toString()) // Use text property to get the content
            model.setId(id)

            // Update the data in the database
            dbHandler.UpdateData(model)

            Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show()

            // Finish the activity to return to the previous screen
            finish()
        }

        Deletebutton.setOnClickListener {
            val model = Model()
            model.setId(id)
            // Assuming idToDelete is the ID of the row you want to delete
            dbHandler.DeleteData(model)
            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show()
        }

    }
}
