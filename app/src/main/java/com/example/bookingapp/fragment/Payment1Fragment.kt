package com.example.bookingapp.fragment

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookingapp.Adapter.DetailOrderAdapter1
import com.example.bookingapp.Adapter.TienichAdapter
import com.example.bookingapp.ApiService
import com.example.bookingapp.Model.DetailOrderModel
import com.example.bookingapp.Model.ModelSlide
import com.example.bookingapp.R
import com.example.bookingapp.ServiceGenerator
import retrofit2.Call
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Payment1Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
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
        val view : View = inflater.inflate(R.layout.fragment_payment1, container, false)
        val recyclerView1 = view.findViewById<RecyclerView>(R.id.recyclerPayment1)

        val sharePreference = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        val getID = sharePreference.getString("ID","").toString()
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val detail = serviceGenerator.getAllDetailPayment1(getID.toInt(),1)
        detail.enqueue(object :
            retrofit2.Callback<MutableList<DetailOrderModel>> {
            override fun onResponse(
                call: Call<MutableList<DetailOrderModel>>,
                response: Response<MutableList<DetailOrderModel>>
            ) {
                if (response.isSuccessful){
                    val imageList = ArrayList<DetailOrderModel>() // Create image list
                    recyclerView1.apply {
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        adapter = DetailOrderAdapter1(response.body()!!)
                    }


                }
            }

            override fun onFailure(call: Call<MutableList<DetailOrderModel>>, t: Throwable) {
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
         * @return A new instance of fragment Payment2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Payment2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}