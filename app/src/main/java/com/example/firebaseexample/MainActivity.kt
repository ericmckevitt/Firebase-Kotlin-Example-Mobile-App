package com.example.firebaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var buttonSend: Button
    lateinit var textViewName: TextView

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference: DatabaseReference = database.reference.child("Users")
    val reference2: DatabaseReference = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextName)
        buttonSend = findViewById(R.id.buttonSend)
        textViewName = findViewById(R.id.textViewName)

        reference2.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val realName: String = snapshot.child("Users").child("name").value as String

                textViewName.text = realName

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        buttonSend.setOnClickListener {
            val userName: String = editText.text.toString()

            reference.child("userName").setValue(userName)
        }
    }
}