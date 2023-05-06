package com.example.eventmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eventmanagement.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {
    private lateinit var binding: ActivityReadDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {

            val Event_Name: String = binding.etusername.text.toString()
            if (Event_Name.isNotEmpty()) {

                readData(Event_Name)

            } else {

                Toast.makeText(this, "PLease enter the Event Name", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun readData(Event_Name: String) {

        database = FirebaseDatabase.getInstance().getReference("Events")
        database.child(Event_Name).get().addOnSuccessListener {

            if (it.exists()) {

                val Event_Name = it.child("firstName").value
                val Venue = it.child("lastName").value
                val Description= it.child("age").value
                Toast.makeText(this, "Successfuly Read", Toast.LENGTH_SHORT).show()
                binding.etusername.text.clear()
                binding.uploadName.text = Event_Name.toString()
                binding.uploadVenue.text = Venue.toString()
                binding.uploadDescription.text = Description.toString()

            } else {

                Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()


            }

        }.addOnFailureListener {

            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


        }
    }
}
