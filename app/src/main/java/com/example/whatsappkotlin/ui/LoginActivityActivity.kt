package com.example.whatsappkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.whatsappkotlin.MessangerApp
import com.example.whatsappkotlin.R
import com.example.whatsappkotlin.utils.hide
import com.example.whatsappkotlin.utils.show
import com.example.whatsappkotlin.utils.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_activity.*
import kotlinx.android.synthetic.main.activity_messanger_app.*

class LoginActivityActivity : AppCompatActivity() {

    //global variables
    private var userName: String? = null
    private var password: String? = null
    //UI elements
    private var tvForgotPassword: TextView? = null
    private var etuserName: EditText? = null
    private var etPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnCreateAccount: TextView? = null
    private var mProgressBar: ProgressBar? = null
    //Firebase references
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)

initialise()

    }

    private fun initialise() {
        tvForgotPassword = findViewById<View>(R.id.forgot) as TextView
        etuserName = findViewById<View>(R.id.username_login) as EditText
        etPassword = findViewById<View>(R.id.password) as EditText
        btnLogin = findViewById<View>(R.id.loginbtn) as Button
        btnCreateAccount = findViewById<View>(R.id.dont) as TextView
        mProgressBar =findViewById(R.id.progressBar_login)
        mAuth = FirebaseAuth.getInstance()
        tvForgotPassword!!
            .setOnClickListener { startActivity(Intent(this,
                ForgotPasswordActivity::class.java)) }
        btnCreateAccount!!
            .setOnClickListener { startActivity(
                Intent(this,
                RegisterActivity::class.java)
            ) }
        btnLogin!!.setOnClickListener { loginUser() }
    }
    private fun loginUser() {
        userName = etuserName?.text.toString()
        password = etPassword?.text.toString()
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
          progressBar_login.show()
            mAuth!!.signInWithEmailAndPassword(userName!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        progressBar_login.hide()
                        // Sign in success, update UI with signed-in user's information
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        progressBar_login.hide()
                       toast( "Authentication failed.")
                    }
                }
        } else {
           toast("Enter all details")
        }
    }
    private fun updateUI() {
        val intent = Intent(this, MessangerApp::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}