package com.example.bookingapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookingapp.Adapter.DoitacAdapter
import com.example.bookingapp.Adapter.DanhmucAdapter
import com.example.bookingapp.ApiService
import com.example.bookingapp.Model.DanhmucModel
import com.example.bookingapp.Model.DoitacModel
import com.example.bookingapp.R
import com.example.bookingapp.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var DoitacAdapter : DoitacAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel("https://q-xx.bstatic.com/psb/capla/static/media/long_stays_banner_wide.a1b12d47.png", "Năm mới ưu đãi khủng. Nhanh tay đặt ngay nơi lưu trú cho mình."))
        imageList.add(SlideModel("https://q-xx.bstatic.com/xdata/images/xphoto/714x300/182431478.jpeg?k=fcbb6d5552a1d4ff338978206c449077ab8ad696050cfd7e4edef1ddc11225cc&o=", "Đổi gió thôi nào, hãy khám phá những nơi đang được yêu thích hiện nay."))
        imageList.add(SlideModel("https://statics.vinpearl.com/dia-diem-du-lich-da-nang_1657940439.JPG","Các địa điểm nổi bật xung quanh Đà Nẵng."))
        imageList.add(SlideModel("https://statics.vinpearl.com/du-lich-da-nang_1657939501.JPG"))
        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        // Inflate the layout for this fragment

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val recyclerViewDoitac = view.findViewById<RecyclerView>(R.id.recyclerViewDoitac)
        val recyclerViewDanhmuc = view.findViewById<RecyclerView>(R.id.recyclerViewCategory)
        val call = serviceGenerator.getDoitac()
        val category = serviceGenerator.getDanhmuc()

        call.enqueue(object :
            retrofit2.Callback<MutableList<DoitacModel>> {
            override fun onResponse(
                call: Call<MutableList<DoitacModel>>,
                response: Response<MutableList<DoitacModel>>
            ) {
                if (response.isSuccessful){
                    recyclerViewDoitac.apply {
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = DoitacAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<DoitacModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })

        category.enqueue(object :
            retrofit2.Callback<MutableList<DanhmucModel>> {
            override fun onResponse(
                call: Call<MutableList<DanhmucModel>>,
                response: Response<MutableList<DanhmucModel>>
            ) {
                if (response.isSuccessful){
                    recyclerViewDanhmuc.apply {
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = DanhmucAdapter(response.body()!!)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<DanhmucModel>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }

        })

        return view
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}