package com.example.databasefirebase

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn = findViewById<Button>(R.id.button)
        val name = findViewById<TextInputEditText>(R.id.name)
        val email = findViewById<TextInputEditText>(R.id.email)
        val password = findViewById<TextInputEditText>(R.id.password)
        val uniqueid = findViewById<TextInputEditText>(R.id.uniqueid)
        val signinbtn = findViewById<Button>(R.id.signin)

        signinbtn.setOnClickListener {
            val signInActivity = Intent(this, SignIn::class.java)
            startActivity(signInActivity)
        }

    btn.setOnClickListener {
        val Name= name.text.toString()
        val Email = email.text.toString()
        val Password = password.text.toString()
        val UniqueId = uniqueid.text.toString()

        val user = Users(Email, Name , Password , UniqueId)

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(UniqueId).setValue(user).addOnSuccessListener {
            name.text?.clear()
            email.text?.clear()
            password.text?.clear()
            uniqueid.text?.clear()
            Toast.makeText(this,"User Added Successfully !", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Failed to Add User", Toast.LENGTH_SHORT).show()
        }





    }

    }
}