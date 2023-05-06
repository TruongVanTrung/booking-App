package com.example.bookingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookingapp.Adapter.DoitacAdapter
import com.example.bookingapp.databinding.ActivityMainBinding
import com.example.bookingapp.fragment.CategoryFragment
import com.example.bookingapp.fragment.HomeFragment
import com.example.bookingapp.fragment.UserFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private var fragment: Fragment?=null
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)


        var bottomBar = findViewById<ChipNavigationBar>(R.id.bottomBar)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.relative_main, HomeFragment())
                .commit()
            bottomBar.setItemSelected(R.id.home)
        }

        bottomBar.setOnItemSelectedListener {
            when (it) {
                R.id.home -> {
                    fragment = HomeFragment()
                }
                R.id.discount -> {

                }
                R.id.cart -> {

                }
                R.id.category -> {
                    fragment = CategoryFragment()
                }
                R.id.me -> {
                    fragment = UserFragment()
                }
            }
            //true
            fragment!!.let {
                supportFragmentManager.beginTransaction().replace(R.id.relative_main,fragment!!).commit()
            }

        }

    }
}