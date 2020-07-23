package com.example.whatsappkotlin.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.view.*

fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_LONG)
}
fun ProgressBar.show(){
    visibility= View.VISIBLE

}
fun ProgressBar.hide(){
       visibility= View.GONE

}
