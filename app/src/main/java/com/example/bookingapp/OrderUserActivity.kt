package com.example.bookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.bookingapp.Adapter.ViewPagerAdapter
import com.example.bookingapp.fragment.Payment1Fragment
import com.example.bookingapp.fragment.Payment2Fragment
import com.example.bookingapp.fragment.Payment3Fragment
import com.google.android.material.tabs.TabLayout

class OrderUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_user)
        getSupportActionBar()?.hide()
        setUpTabs()

        val back : ImageView = findViewById(R.id.logoBack)
        back.setOnClickListener{
            onBackPressed();
        }
    }

    private fun setUpTabs() {

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Payment1Fragment(),"Mới đặt")
        adapter.addFragment(Payment2Fragment(),"Đã xác nhận")
        adapter.addFragment(Payment3Fragment(),"Đã hoàn thành")
        val viewpager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabLayout)
        viewpager.adapter=adapter
        tabs.setupWithViewPager(viewpager)

       // val viewCountProduct = findViewById<TextView>(R.id.viewTotalOrder)
        //val viewCountPrice = findViewById<TextView>(R.id.viewTotalProduct)



//        tabs.getTabAt(0)!!.set
    }
}