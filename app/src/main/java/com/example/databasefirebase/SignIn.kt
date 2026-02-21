package com.example.databasefirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.databasefirebase.SignIn.Companion.KEY1
import com.example.databasefirebase.SignIn.Companion.KEY2
import com.example.databasefirebase.SignIn.Companion.KEY3
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1 = "com.example.day16database.SignIn.name"
        const val KEY2 = "com.example.day16database.SignIn.email"
        const val KEY3 = "com.example.day16database.SignIn.id"
    }
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userid = findViewById<TextInputEditText>(R.id.useruniqueid)
        val btn = findViewById<Button>(R.id.SignInBtn)

        btn.setOnClickListener {
            val userId = userid.text.toString()
            if(userId.isEmpty()){
                Toast.makeText(this,"Enter your user Id",Toast.LENGTH_SHORT).show()
            } else{
                readData(userId)
            }
        } }
}
private fun SignIn.readData(userId: String) {
    database = FirebaseDatabase.getInstance().getReference("Users")

    database.child(userId).get().addOnSuccessListener {
        if(it.exists()){
            val name = it.child("name").value
            val email = it.child("email").value
            val uniqueid = it.child("uniqueid").value

            val intentWelcome = Intent(this, WelcomeActivity::class.java)

            intentWelcome.putExtra(KEY1, name.toString())
            intentWelcome.putExtra(KEY2, email.toString())
            intentWelcome.putExtra(KEY3, uniqueid.toString())
            startActivity(intentWelcome)
        }else{
            Toast.makeText(this,"User does not exists",Toast.LENGTH_SHORT).show()
        }

    }.addOnFailureListener {
        Toast.makeText(this,"Server Failed",Toast.LENGTH_SHORT).show()
    }

}
