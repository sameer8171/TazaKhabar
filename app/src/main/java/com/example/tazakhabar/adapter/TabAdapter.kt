package com.example.tazakhabar.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tazakhabar.fragment.*

class TabAdapter(fm:FragmentManager
):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 7
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{ BusinessFragment() }
             1->{EntertainmentFragment()}
             2->{GeneralFragment()}
             3->{HealthFragment()}
             4->{ScienceFragment()}
             5->{SportsFragment()}
             6->{TechnologyFragment()}
            else->{BusinessFragment()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 ->{
                return "Business"
            }
            1 ->{
                return "Entertainment"
            }
            2 ->{
                return "General"
            }
            3 ->{
                return "Health"
            }
            4 ->{
                return "Science"
            }
            5 ->{
                return "Sports"
            }
            6 ->{
                return "Technology"
            }

        }
        return super.getPageTitle(position)
    }

}