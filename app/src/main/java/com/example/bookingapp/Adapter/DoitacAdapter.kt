package com.example.bookingapp.Adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bookingapp.Model.DoitacModel
import com.example.bookingapp.R
import com.example.bookingapp.fragment.CategoryFragment
import com.example.bookingapp.fragment.DetailDoitacaFragment
import com.squareup.picasso.Picasso

class DoitacAdapter(val doitacModel: MutableList<DoitacModel>):RecyclerView.Adapter<DoitacAdapter.DoitacViewHolder>()  {

    inner class DoitacViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        val imagedoitac : ImageView = itemView.findViewById(R.id.imagedoitac)
        val tendoitac : TextView = itemView.findViewById(R.id.nameDoitac)
        val tendanhmuc : TextView = itemView.findViewById(R.id.tendanhmuc)
        val location : TextView = itemView.findViewById(R.id.location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoitacViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_doitac, parent, false)
        return DoitacViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoitacViewHolder, position: Int) {
        Picasso.get().load(doitacModel[position].image?.get(0)).into(holder.imagedoitac)
        holder.tendoitac.text = doitacModel[position].name
        holder.tendanhmuc.text = doitacModel[position].namecategory
        holder.location.text = doitacModel[position].address.toString().take(48)
        Log.e("LinkImage", doitacModel[position].image.toString())

        holder.itemView.setOnClickListener { view ->
            val id : Int ?= doitacModel[position].id
            val tendoitac : String? = doitacModel[position].name
            val tendanhmuc : String? = doitacModel[position].namecategory
            val location : String? = doitacModel[position].address
            val note : String? = doitacModel[position].note
            val utilities : ArrayList<String>? = doitacModel[position].utilities
            val popular : ArrayList<String>? = doitacModel[position].popular
            val image : ArrayList<String>? = doitacModel[position].image

            val bundle = Bundle()
            if (id != null) {
                bundle.putInt("id", id)
            }
            bundle.putString("tendoitac", tendoitac)
            bundle.putString("tendanhmuc", tendanhmuc)
            bundle.putString("location", location)
            bundle.putString("note", note)
            bundle.putStringArrayList("utilities", utilities)
            bundle.putStringArrayList("popular", popular)
            bundle.putStringArrayList("image", image)
            val activity =view.context as AppCompatActivity
            val ac = view.context as FragmentActivity
            val detailDoitacFragment = DetailDoitacaFragment()
            detailDoitacFragment.arguments = bundle

            val f: Fragment = ac.supportFragmentManager.findFragmentById(R.id.relative_main)!!

            if (f is CategoryFragment){

                Log.e("current fragment","1")
                activity.supportFragmentManager.beginTransaction().replace(R.id.homeCate,detailDoitacFragment).addToBackStack(null).commit()

            }else{
                activity.supportFragmentManager.beginTransaction().replace(R.id.layoutHome,detailDoitacFragment).addToBackStack(null).commit()
            }


        }

    }

    override fun getItemCount(): Int {
        return doitacModel.size
    }


}
