package com.example.bookingapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.bookingapp.LoginActivity
import com.example.bookingapp.OrderUserActivity
import com.example.bookingapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
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
        val view : View = inflater.inflate(R.layout.fragment_user, container, false)
        val login = view.findViewById<ImageView>(R.id.login)
        val book = view.findViewById<TextView>(R.id.donhang)
        val logout = view.findViewById<TextView>(R.id.logout)
        val email = view.findViewById<TextView>(R.id.email)
        val sharePreference =  activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
        val getEmail = sharePreference.getString("Email","").toString()
        val getID = sharePreference.getString("ID","").toString()
        val editor =  sharePreference.edit().putInt("activity",0)
        editor.apply()
        Log.e("local",getEmail)
        Log.e("responseID", getID)
        email.text = getEmail
        login.setOnClickListener {
            activity?.let {
                val getA = sharePreference.getInt("activity",3).toString()
                Log.e("activity", getA)
                it.startActivity(Intent(it, LoginActivity::class.java))
//                activity?.finish()
            }
        }

        book.setOnClickListener {
            activity?.let {
                it.startActivity(Intent(it, OrderUserActivity::class.java))
                //activity?.finish()
            }
        }

        logout.setOnClickListener {
            activity?.let {
//                it.startActivity(Intent(it, LoginActivity::class.java))
//                activity?.finish()
                if (getID != null){
                    email.text = "Email"
                    sharePreference.edit().remove("Email").commit();
                    sharePreference.edit().remove("ID").commit();
                }else{
                    Toast.makeText(activity,"Bạn chưa đăng nhập",Toast.LENGTH_LONG).show()
                }
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}