package com.example.whatsappkotlin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappkotlin.R
import com.example.whatsappkotlin.modelClasses.Users
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_messanger_app.*
import kotlinx.android.synthetic.main.activity_messanger_app.view.*

class UserAdapter (private val context: Context,private val userList:List<Users>,private var ischatCheck:Boolean):
    RecyclerView.Adapter<UserAdapter.viewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.viewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.user_search_itemlayout, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserAdapter.viewHolder, position: Int) {
        val users:Users =userList[position]
        holder.username.text=users.getUsername()
        Picasso.get().load(users.getProfile()).placeholder(R.drawable.profile_icon).into(holder.profileImageView)

    }



class viewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
    var username: TextView
    var profileImageView: CircleImageView
    var onlineStatus: CircleImageView
    var offlinetatus: CircleImageView
    var lastMessageTxt: TextView

    init {
        username = itemview.findViewById(R.id.user_name)
        profileImageView = itemview.findViewById(R.id.profile_image_serach)
        onlineStatus = itemview.findViewById(R.id.imageonline_serach)
        offlinetatus = itemview.findViewById(R.id.imageoffline_serach)
        lastMessageTxt = itemview.findViewById(R.id.message_last)


    }
}
}