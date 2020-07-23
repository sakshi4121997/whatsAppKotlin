package com.example.whatsappkotlin.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappkotlin.Adapter.UserAdapter
import com.example.whatsappkotlin.R
import com.example.whatsappkotlin.modelClasses.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

  private var userAdapter:UserAdapter?=null
    private var userlist:List<Users>?=null
    private var recyclerView:RecyclerView?=null
    private var searchEd:EditText?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_search, container, false)
        searchEd=view.findViewById(R.id.searchUser)
        recyclerView=view.findViewById(R.id.searchrecycler)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager=LinearLayoutManager(context)

        userlist=ArrayList()
        retrieveAllUsers()
        searchEd!!.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(cs: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchforUser(cs.toString().toLowerCase())

            }

        })


        return view
    }

    //when the user not search any user it show all the users

    private fun retrieveAllUsers() {

        val firebaseUserId= FirebaseAuth.getInstance().currentUser!!.uid
        val  refUser= FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)
         refUser.addValueEventListener(object :ValueEventListener{
             override fun onCancelled(p0: DatabaseError) {

             }

             override fun onDataChange(p0: DataSnapshot) {
                 if(searchEd!!.text.toString()== "") {
                     (userlist as ArrayList<Users>).clear()
                     for (snapshot in p0.children) {
                         val user: Users? = snapshot.getValue(Users::class.java)
                         if (!(user!!.getUid()).equals(firebaseUserId)) {
                             (userlist as ArrayList<Users>).add(user)

                         }
                     }
                     userAdapter = UserAdapter(context!!, userlist!!, false)
                     recyclerView!!.adapter = userAdapter
                 }
             }

         })

    }

    //when the user  search any user it show that search  users

    private fun searchforUser(str:String){

        val firebaseUserId= FirebaseAuth.getInstance().currentUser!!.uid
        val  queryUsers= FirebaseDatabase.getInstance().reference.child("Users").orderByChild("search").startAt(str).endAt(str + "\uf8ff")

        queryUsers.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                for ( snapshot in p0.children){
                    val user:Users?=snapshot.getValue(Users::class.java)
                    if(!(user!!.getUid()).equals(firebaseUserId)){
                        (userlist as ArrayList<Users>).add(user)

                    }
                }
                userAdapter= UserAdapter(context!!,userlist!!,false)
                recyclerView!!.adapter=userAdapter


            }

        })

    }


}