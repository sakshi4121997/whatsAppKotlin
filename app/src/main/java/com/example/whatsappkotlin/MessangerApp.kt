package com.example.whatsappkotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.whatsappkotlin.modelClasses.Users
import com.example.whatsappkotlin.ui.LoginActivityActivity
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_messanger_app.*

class MessangerApp : AppCompatActivity() {

    var refUser: DatabaseReference?=null
    var firebaseUser: FirebaseUser?=null

    var tab_layout : TabLayout? = null
    var viewpager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messanger_app)

        firebaseUser=FirebaseAuth.getInstance().currentUser
        refUser=FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)


        setSupportActionBar(toolbar_main)
        val toolbar:Toolbar=findViewById(R.id.toolbar_main)
        supportActionBar!!.title= ""


        tab_layout = findViewById(R.id.tab_layout)
        viewpager = findViewById(R.id.viewpager)

        val adapter = MyAdapter(this, supportFragmentManager, tab_layout!!.tabCount)
        viewpager!!.adapter = adapter

        viewpager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        tab_layout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        // ---------------------display username and images-----------
        refUser!!.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    val users:Users? = p0.getValue(Users::class.java)
                    user_name.text= users!!.getUsername()
                    Picasso.get().load(users.getProfile()).placeholder(R.drawable.profile_icon).into(profile_image)
                }
            }

        })
        // ---------------------display username and images-----------

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout_menu -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivityActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }




}
