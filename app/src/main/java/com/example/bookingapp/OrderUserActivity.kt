package com.example.bookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.bookingapp.Adapter.ViewPagerAdapter
import com.example.bookingapp.Model.ResponseMessageModel
import com.example.bookingapp.Model.TotalPriceModel
import com.example.bookingapp.fragment.Payment1Fragment
import com.example.bookingapp.fragment.Payment2Fragment
import com.example.bookingapp.fragment.Payment3Fragment
import com.example.bookingapp.fragment.Payment4Fragment
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

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
        val total_Qty : TextView = findViewById(R.id.viewTotalOrder)
        val total_Price : TextView = findViewById(R.id.viewTotalProduct)
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val getId = sharedPreferences.getString("ID","").toString()
        Log.e("id_total", getId)
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        serviceGenerator.getTotalPaymentUser(getId.toInt()).enqueue(object :
            retrofit2.Callback<TotalPriceModel> {
            override fun onResponse(
                call: Call<TotalPriceModel>,
                response: Response<TotalPriceModel>
            ) {
                if (response.isSuccessful){
                    Log.e("responseTotal", response.body()!!.toString())
                    total_Price.text = response.body()!!.total_price.toString()
                    total_Qty.text = response.body()!!.total_quantity.toString()
                }
            }

            override fun onFailure(call: Call<TotalPriceModel>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Payment1Fragment(),"Mới đặt")
        adapter.addFragment(Payment2Fragment(),"Đã xác nhận")
        adapter.addFragment(Payment4Fragment(),"Đã nhận")
        adapter.addFragment(Payment3Fragment(),"Đã hoàn thành")
        val viewpager = findViewById<ViewPager>(R.id.viewPager)
        val tabs = findViewById<TabLayout>(R.id.tabLayout)
        viewpager.adapter=adapter
        tabs.setupWithViewPager(viewpager)

    }
}
