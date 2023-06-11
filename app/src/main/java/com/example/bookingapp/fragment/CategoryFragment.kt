package com.example.bookingapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.Adapter.DanhmucAdapter
import com.example.bookingapp.Adapter.DoitacAdapter
import com.example.bookingapp.ApiService
import com.example.bookingapp.Model.DanhmucModel
import com.example.bookingapp.Model.DoitacModel
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
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
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

        val view : View = inflater.inflate(R.layout.fragment_category, container, false)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val recyclerViewDoitac = view.findViewById<RecyclerView>(R.id.recyclerViewDoitac)
        val recyclerViewDanhmuc = view.findViewById<RecyclerView>(R.id.recyclerViewCategory)
        val text_cate = view.findViewById<TextView>(R.id.text_cate)
        val category = serviceGenerator.getDanhmuc()

        val click_back = view.findViewById<ImageView>(R.id.logo)
        click_back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        var id = arguments?.getInt("id")
        var tendanhmuc = arguments?.getString("tendanhmuc")
        text_cate.text = tendanhmuc

        if (id != null ){
            Log.e("ID", id.toString())
            val doitac = serviceGenerator.getCategoryDetail(id)
            doitac.enqueue(object :
                retrofit2.Callback<MutableList<DoitacModel>> {
                override fun onResponse(
                    call: Call<MutableList<DoitacModel>>,
                    response: Response<MutableList<DoitacModel>>
                ) {
                    if (response.isSuccessful){
                        Log.e("resP", response.body()!!.toString())
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
        }
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



        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}