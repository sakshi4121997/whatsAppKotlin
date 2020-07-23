package com.example.whatsappkotlin

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.example.whatsappkotlin.fragments.ChatFragment
import com.example.whatsappkotlin.fragments.SearchFragment
import com.example.whatsappkotlin.fragments.SettingFragment


class MyAdapter(private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                return ChatFragment()
            }
            1 -> {
                return SearchFragment()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                return SettingFragment()
            }
            else -> return ChatFragment()
        }
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}