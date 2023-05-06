package com.example.bookingapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookingapp.Adapter.DoitacAdapter
import com.example.bookingapp.Model.DoitacModel
import retrofit2.Call
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel("https://cdn.tgdd.vn/Files/2017/11/14/1041538/ban-da-biet-cach-phan-biet-trai-thom-trai-khom-va-trai-dua-202104302123366561.jpg", "Trái dứa là sản phẩm bán chạy và được ưu tiên bán hàng nhất trên ứng dụng."))
        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRABoUlm2CMza9nXWml7KaJzMc_1xzHR8zygdpjSJY756GESmk7kFccfx22IyQSTWh5-I4&usqp=CAU", "Vì sức khoẻ gia đình, hãy chọn mua nông sản sạch."))
        imageList.add(SlideModel("https://lh3.googleusercontent.com/proxy/OPUjkZIv0vYuxebOL-vjrCIYvhIS6pC6WfK-4jG-bjjdQfiLo3DjL2LuhdmD5Gq4RrfGdAcmVYfNYhJrNQLtD24DIN01vjY", "Trái dứa là sản phẩm bán chạy và được ưu tiên bán hàng nhất trên ứng dụng."))
        imageList.add(SlideModel("https://lh3.googleusercontent.com/proxy/NqzWvt-U2XateenFaGUuNcIf33SKtAg7qQGmM9FOfMhjrmn8p-nYwYhd6vqGBKsQhNN6aDTpPl3ydpV7oo8FlsAIFwkKwK7AlBZvOnthG75ZD8hVGHgjo6w", "Muốn đảm tìm thực phẩm sạch cho gia đình liên hệ chúng tôi."))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val recyclerViewDoitac = findViewById<RecyclerView>(R.id.recyclerViewDoitac)
        val call = serviceGenerator.getDoitac()

        call.enqueue(object :
            retrofit2.Callback<MutableList<DoitacModel>> {
            override fun onResponse(
                call: Call<MutableList<DoitacModel>>,
                response: Response<MutableList<DoitacModel>>
            ) {
                if (response.isSuccessful){
                    recyclerViewDoitac.apply {
                        layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = DoitacAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<DoitacModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })

    }

}