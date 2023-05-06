package com.example.bookingapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import com.example.bookingapp.Model.PostLoginModel
import com.example.bookingapp.Model.ResponseMessageModel
import com.example.bookingapp.databinding.ActivityLoginBinding
import com.example.bookingapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.security.Key

class LoginActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding ?= null
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_login)

        val button_login = findViewById<Button>(R.id.button_login)
        val logo = findViewById<ImageView>(R.id.logo)
        val register = findViewById<TextView>(R.id.register)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val intent = Intent(this, MainActivity::class.java)


        button_login.setOnClickListener {
            val email : EditText = findViewById(R.id.edit_text1)
            val pass : EditText = findViewById(R.id.edit_text2)
            if (email.text.toString().isNotEmpty() &&  pass.text.toString().isNotEmpty()){
                val obj = PostLoginModel(email.text.toString(),pass.text.toString())
                val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
                serviceGenerator.sendReqLogin(obj).enqueue(object :
                    retrofit2.Callback<ResponseMessageModel> {
                    override fun onResponse(
                        call: Call<ResponseMessageModel>,
                        response: Response<ResponseMessageModel>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(this@LoginActivity,response.body()!!.message.toString(),Toast.LENGTH_LONG).show()

                            Log.e("response", response.body()!!.message.toString())
                            Log.e("responseID", response.body()!!.id.toString())
                            progressBar.setVisibility(View.VISIBLE)

                            sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("ID",response.body()!!.id.toString())
                            editor.putString("Email",email.text.toString() )
                            editor.apply()
                            val getEmail = sharedPreferences.getString("Email","").toString()
                            val getId = sharedPreferences.getString("ID","").toString()
                            Log.e("locallll",getEmail)
                            Log.e("locall",getId)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<ResponseMessageModel>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("error", t.message.toString())
                        Toast.makeText(this@LoginActivity,t.message.toString(),Toast.LENGTH_LONG).show()
                        //progressBar.setVisibility(View.VISIBLE)
                    }

                })
            }else{
                Toast.makeText(this,"Nhập đầy đủ thông tin",Toast.LENGTH_LONG).show()
                //progressBar.setVisibility(View.VISIBLE)
            }

        }

        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        logo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}