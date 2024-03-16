package com.example.kotlinapp1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DbHandler = Dbhandler(this)

        val editText1 = findViewById<EditText>(R.id.Name)
        val editText2 = findViewById<EditText>(R.id.Description)
        val button = findViewById<Button>(R.id.Adddata)
        val viewbtn=findViewById<Button>(R.id.view)

        button.setOnClickListener {
            val text1 = editText1.text.toString()
            val text2 = editText2.text.toString()

            if (text1.isEmpty()) {
                Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show()
            } else if (text2.isEmpty()) {
                Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show()

            } else {
                val model = Model()
                model.setName(text1)
                model.setDeec(text2)

                DbHandler.insertData(model)

                Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show()
            }
        }


        viewbtn.setOnClickListener {


            val intent = Intent(this, Viewdata::class.java)
            startActivity(intent)
        }

    }
}