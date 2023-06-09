package com.example.bookingapp.fragment

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.Display.Mode
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
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookingapp.Adapter.DoitacAdapter
import com.example.bookingapp.Adapter.PhobienAdapter
import com.example.bookingapp.Adapter.RoomAdapter
import com.example.bookingapp.Adapter.TienichAdapter
import com.example.bookingapp.ApiService
import com.example.bookingapp.Model.*
import com.example.bookingapp.R
import com.example.bookingapp.ServiceGenerator
import com.google.gson.JsonArray
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailDoitacaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailDoitacaFragment : Fragment() {
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

        val view : View = inflater.inflate(R.layout.fragment_detail_doitaca, container, false)

        val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
        val recyclerViewTienich = view.findViewById<RecyclerView>(R.id.recyclerViewtienich)
        val recyclerViewPhobien = view.findViewById<RecyclerView>(R.id.recyclerViewPhobien)
        val recyclerViewRoom = view.findViewById<RecyclerView>(R.id.recyclerViewRoom)
        val text_tendoitac = view.findViewById<TextView>(R.id.nameDoitac)
        val text_tendanhmuc = view.findViewById<TextView>(R.id.tendanhmuc)
        val text_location   = view.findViewById<TextView>(R.id.location)
        val gioithieu   = view.findViewById<TextView>(R.id.ghichu)

        val click_back = view.findViewById<ImageView>(R.id.logo)
        //val text_cate = view.findViewById<TextView>(R.id.text_cate)


        var id = arguments?.getInt("id")
        var tendoitac = arguments?.getString("tendoitac")
        var tendanhmuc = arguments?.getString("tendanhmuc")
        var location = arguments?.getString("location")
        var info = arguments?.getString("note")
        var utilities = arguments?.getStringArrayList("utilities")
        var popular = arguments?.getStringArrayList("popular")
        var image = arguments?.getStringArrayList("image")

        text_tendoitac.text = tendoitac
        text_tendanhmuc.text = tendanhmuc
        text_location.text = location
        gioithieu.text = info

        val imageList = ArrayList<SlideModel>() // Create image list
        for (i in image!!){
            Log.e("image", i.toString())
            imageList.add(SlideModel(i.toString()))
        }

        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)

        click_back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        if (id != null){
//
            val utilitiList = ArrayList<TienichModel>() // Create image list
            for (i in utilities!!){
                Log.e("utiliti", i.toString())
                utilitiList.add(TienichModel(i.toString()))
            }
            recyclerViewTienich.apply {
                layoutManager = GridLayoutManager(activity,2)
                adapter = TienichAdapter(utilitiList!!)
            }

            val popularList = ArrayList<PhobienModel>() // Create image list
            for (i in popular!!){
                Log.e("popular", i.toString())
                if(i != null) {
                    popularList.add(PhobienModel(i.toString()))
                }else{
                    Log.e("popular_null", "null")
                }
            }
            recyclerViewPhobien.apply {
                layoutManager = GridLayoutManager(activity,2)
                adapter = PhobienAdapter(popularList!!)
            }

            val roomAll = serviceGenerator.getAllRoomDoitac(id)
            roomAll.enqueue(object :
                retrofit2.Callback<MutableList<RoomModel>> {
                override fun onResponse(
                    call: Call<MutableList<RoomModel>>,
                    response: Response<MutableList<RoomModel>>
                ) {
                    if (response.isSuccessful){
                        recyclerViewRoom.apply {
                            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                            adapter = RoomAdapter(response.body()!!)
                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<RoomModel>>, t: Throwable) {
                    t.printStackTrace()
                    Log.e("error", t.message.toString())
                }

            })


        }


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
         * @return A new instance of fragment DetailDoitacaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailDoitacaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}