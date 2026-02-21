package com.example.databasefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignIn.KEY1)
        val mail = intent.getStringExtra(SignIn.KEY2)
        val userId = intent.getStringExtra(SignIn.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tVWelcome)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val idText = findViewById<TextView>(R.id.tvUnique)

        welcomeText.text = "Welcome $name"
        mailText.text = "Mail : $mail"
        idText.text = "UserId : $userId"

    }
}