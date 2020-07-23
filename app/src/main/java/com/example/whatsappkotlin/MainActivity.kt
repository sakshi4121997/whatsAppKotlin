package com.example.whatsappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.whatsappkotlin.ui.LoginActivityActivity
import com.example.whatsappkotlin.ui.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user = FirebaseAuth.getInstance().currentUser;
        if (user != null) {
            Handler().postDelayed({

                // User is signed in, send to mainmenu
                startActivity(Intent(this, MessangerApp::class.java))
                finish()
            }, SPLASH_TIME_OUT)
        }else {
            // User is signed out, send to register/login
            startActivity(Intent(this, LoginActivityActivity::class.java))
            finish()
        }
    }
}