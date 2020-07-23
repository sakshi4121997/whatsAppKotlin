package com.example.whatsappkotlin.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.whatsappkotlin.MessangerApp
import com.example.whatsappkotlin.R
import com.example.whatsappkotlin.utils.hide
import com.example.whatsappkotlin.utils.show
import com.example.whatsappkotlin.utils.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    //UI elements
    private var etuserName: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var alreadyHave: TextView? = null
    private var btnCreateAccount: Button? = null
    private var mProgressBar: ProgressBar? = null

   //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private val TAG = "CreateAccountActivity"
    //global variables
    private var userName: String? = null

    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initialise()
        alreadyHave?.setOnClickListener {
            val intent = Intent(this, LoginActivityActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }
    }

    private fun initialise() {
        etuserName = findViewById<View>(R.id.username_register) as EditText
        etEmail = findViewById<View>(R.id.email_register) as EditText
        etPassword = findViewById<View>(R.id.pwd_register) as EditText
        alreadyHave = findViewById<View>(R.id.already) as TextView
        btnCreateAccount = findViewById<View>(R.id.signup) as Button


        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        btnCreateAccount!!.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount() {
        userName = etuserName?.text.toString()
        email = etEmail?.text.toString()
        password = etPassword?.text.toString()

        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(password)  ) {
            progressBar_register.show()
            mAuth!!.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        progressBar_register.hide()
                        // Sign in success, update UI with the signed-in user's information
                        toast("succesfully login")
                        val userId = mAuth!!.currentUser!!.uid
                        //Verify Email
                        verifyEmail();
                        //update user profile information
                        val currentUserDb = mDatabaseReference!!.child(userId)
                        currentUserDb.child("email").setValue(email)
                        currentUserDb.child("username").setValue(userName)
                        currentUserDb.child("profile").setValue("https://firebasestorage.googleapis.com/v0/b/whatsapp-55405.appspot.com/o/person.jpg?alt=media&token=255e5903-6e19-4a49-b579-c6595af71504")
                        currentUserDb.child("cover").setValue("https://firebasestorage.googleapis.com/v0/b/whatsapp-55405.appspot.com/o/person.jpg?alt=media&token=255e5903-6e19-4a49-b579-c6595af71504")
                        currentUserDb.child("status").setValue("offline")
                        currentUserDb.child("search").setValue(userName.toString().toLowerCase())
                        currentUserDb.child("instagram").setValue("https://m.facebook.com")
                        currentUserDb.child("facebook").setValue("https://m.instagram.com")
                        currentUserDb.child("uid").setValue(userId)
                        currentUserDb.child("websites").setValue("https://m.google.com")
                        updateUserInfoAndUI()

                    } else {
                        // If sign in fails, display a message to the user.
                        progressBar_register.hide()
                        toast("Authentication failed.")

                    }
                }

        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }
    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser;
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Verification email sent to " + mUser.getEmail(),
                        Toast.LENGTH_SHORT).show()
                } else {
                    toast("Failed to send verification email.")
                }
            }
    }
    private fun updateUserInfoAndUI() {
        //start next activity
        val intent = Intent(this, MessangerApp::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}