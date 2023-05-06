package com.example.eventmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eventmanagement.databinding.ActivityMainBinding
import com.example.eventmanagement.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {

            val name = binding.uploadName.text.toString()
            val venue = binding.uploadVenue.text.toString()
            val description = binding.uploadDescription.text.toString()

            updateData(name, venue, description)

        }

    }

    private fun updateData(EventName: String, Venue: String, Description: String) {
        database = FirebaseDatabase.getInstance().getReference("Events")
        val user = mapOf<String, String>(
            "EventName" to EventName,
            "Venue" to Venue,
            "Description" to Description
        )

        database.child(EventName).updateChildren(user).addOnSuccessListener {
            binding.uploadName.text.clear()
            binding.uploadVenue.text.clear()
            binding.uploadDescription.text.clear()

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }
    }
}







