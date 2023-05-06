package com.example.bookingapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.Adapter.DetailOrderAdapter2
import com.example.bookingapp.ApiService
import com.example.bookingapp.Model.DetailOrderModel
import com.example.bookingapp.R
import com.example.bookingapp.ServiceGenerator
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Payment3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Payment3Fragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        val view : View = inflater.inflate(R.layout.fragment_payment3, container, false)

        val recyclerView3 = view.findViewById<RecyclerView>(R.id.recyclerPayment3)

        val sharePreference = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        val getID = sharePreference.getString("ID","").toString()
        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val detail = serviceGenerator.getAllDetailPayment1(getID.toInt(),3)
        detail.enqueue(object :
            retrofit2.Callback<MutableList<DetailOrderModel>> {
            override fun onResponse(
                call: Call<MutableList<DetailOrderModel>>,
                response: Response<MutableList<DetailOrderModel>>
            ) {
                if (response.isSuccessful){
                    val imageList = ArrayList<DetailOrderModel>() // Create image list
                    recyclerView3.apply {
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        adapter = DetailOrderAdapter2(response.body()!!)
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
         * @return A new instance of fragment Payment3Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Payment3Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}