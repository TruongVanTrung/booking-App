package com.example.bookingapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.bookingapp.Model.PostLoginModel
import com.example.bookingapp.Model.PymentModel
import com.example.bookingapp.Model.ResponseMessageModel
import com.google.android.material.internal.ContextUtils.getActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import java.util.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_payment)

        val tenphong : TextView = findViewById(R.id.tenphong)
        val ghichu : TextView = findViewById(R.id.note)
        val giuong : TextView = findViewById(R.id.text_giuong)
        val nguoi : TextView = findViewById(R.id.text_people)
        val price : TextView = findViewById(R.id.text_price)
        val imageRoom : ImageView = findViewById(R.id.imageRoom)

        val intent = intent
        Picasso.get().load(intent.getStringExtra("img")).into(imageRoom)

        var id = intent.getStringExtra("id")
        var tenroom = intent.getStringExtra("tenroom")
        var songuoi = intent.getStringExtra("songuoi")
        var sogiuong = intent.getStringExtra("sogiuong")
        var gia = intent.getStringExtra("gia")
        var note = intent.getStringExtra("note")

        tenphong.text = tenroom
        ghichu.text = note
        nguoi.text = songuoi
        giuong.text = sogiuong
        price.text = gia
        val date_nhan : EditText = findViewById(R.id.edit_date1)
        val date_tra : EditText = findViewById(R.id.edit_date2)

        val click_back =findViewById<ImageView>(R.id.logoBack)
        click_back.setOnClickListener {
            onBackPressed();
        }

        val myCalendar = Calendar.getInstance()
        //val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofMonth ->
        val year =  myCalendar.get(Calendar.YEAR)
        val month =  myCalendar.get(Calendar.MONTH)
        val day   = myCalendar.get(Calendar.DAY_OF_MONTH)

        date_nhan.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                date_nhan.setText("" + mYear + "-" + mMonth + 1 + "-" + mDay)
            }, year, month, day)
            dpd.show()
        }
        date_tra.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                date_tra.setText("" + mYear + "-" + mMonth + 1 + "-" + mDay)
            }, year, month, day)
            dpd.show()
        }
         val keyAc : Int = 1
        val editName : EditText = findViewById(R.id.edit_name)
        val editPhone : EditText = findViewById(R.id.edit_phone)
        val editEmail : EditText = findViewById(R.id.edit_email)
        val editCount : EditText = findViewById(R.id.edit_count)

        val sharePreference =  getSharedPreferences("user", Context.MODE_PRIVATE)
        val getID = sharePreference.getString("ID","").toString()

        val submit : Button = findViewById(R.id.next)
        val intentt = Intent(this, MainActivity::class.java)
        submit.setOnClickListener {

            if (editName.text.toString().isNotEmpty() &&  editPhone.text.toString().isNotEmpty()
                &&  editEmail.text.toString().isNotEmpty()
                &&  editCount.text.toString().isNotEmpty()
                &&  date_nhan.text.toString().isNotEmpty()
                &&  date_tra.text.toString().isNotEmpty()
                ){
                val obj = PymentModel(id?.toInt(), getID.toString().toInt() , editName.text.toString() ,editPhone.text.toString(),
                    editEmail.text.toString(), editCount.text.toString().toInt(), date_nhan.text.toString(), date_tra.text.toString())
                val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
                serviceGenerator.sendReqPayment(obj).enqueue(object :
                    retrofit2.Callback<ResponseMessageModel> {
                    override fun onResponse(
                        call: Call<ResponseMessageModel>,
                        response: Response<ResponseMessageModel>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(this@PaymentActivity,response.body()!!.message.toString(),
                                Toast.LENGTH_LONG).show()
                            Log.e("response", response.body()!!.message.toString())
                            startActivity(intentt)

                            //Log.e("responseID", response.body()!!.ID.toString())

                        }
                    }

                    override fun onFailure(call: Call<ResponseMessageModel>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("error", t.message.toString())
                        Toast.makeText(this@PaymentActivity,t.message.toString(), Toast.LENGTH_LONG).show()
                        //progressBar.setVisibility(View.VISIBLE)
                    }

                })
            }else{
                Toast.makeText(this,"Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show()
                //progressBar.setVisibility(View.VISIBLE)
            }

        }
    }
}