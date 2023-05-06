package com.example.eventmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eventmanagement.databinding.ActivityDeleteDataBinding
import com.example.eventmanagement.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.jar.Attributes.Name

class DeleteData : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {

            var name = binding.uploadName.text.toString()
            if (name.isNotEmpty())
                DeleteData()
            else
                Toast.makeText(this,"Please Enter the event name",Toast.LENGTH_SHORT).show()

        }

    }

    private fun deleteData(EventName: String) {
        database = FirebaseDatabase.getInstance().getReference("Events")
        database.child(EventName).removeValue().addOnSuccessListener {

            binding.uploadName.text.clear()
            Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }




    }
}

