package com.example.eventmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eventmanagement.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {

            val name = binding.uploadName.text.toString()
            val venue = binding.uploadVenue.text.toString()
            val description = binding.uploadDescription.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Events")
            val user = User(EventName = null, Venue = null, Description = null)
            database.child(name).setValue(user).addOnSuccessListener {
                binding.uploadName.text.clear()
                binding.uploadVenue.text.clear()
                binding.uploadDescription.text.clear()

                Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"failed",Toast.LENGTH_SHORT).show()
            }

        }
    }
}