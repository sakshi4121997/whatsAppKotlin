package com.example.whatsappkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsappkotlin.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

var mAuth:FirebaseAuth?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view:View= inflater.inflate(R.layout.fragment_setting, container, false)
        mAuth=FirebaseAuth.getInstance()
       /* logoutbtn.setOnClickListener {
            mAuth!!.signOut()
        }*/
        return view
    }

}