package com.example.readingtracking

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.readingtracking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  sharedPreferences: SharedPreferences
    var namePreferences:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBinding()
        setupLoadDAta()


    }

    fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    // aynı zamanda kullanıcı adını save olarak alır
    fun setupLoadDAta(){
       sharedPreferences = this.getSharedPreferences("com.example.readingtracking", MODE_PRIVATE)
       namePreferences=sharedPreferences.getString(Shared.UserName.get,"Fatih")
        if(namePreferences ==""){
            binding.result.text="İlk kullanıcı sizsiniz "

        }else{
            binding.result.text="en son giriş yapan kişi=${namePreferences}"
        }
    }
    //xml arasındaki geçiş butonumuz
    // ve kullanıcı adı save işlemi
    fun login(view:View){

        val name = binding.PersonName.text.toString()
        if(name ==null ){
            binding.result.text="herhangi bir deger girilmedi !!!!"

        }else{
            binding.result.text="kullanıcı isminiz: "+name
            sharedPreferences.edit().putString(Shared.UserName.get,name).apply()
        }
        //uyaqrı mesajı
        val alert=AlertDialog.Builder(this)
        alert.setTitle("ilk ")
        alert.setMessage("bilgileriniz kaydedilmektedir")
        // kullanıcı eğer onay verirse aktarılma işlemi yapılmaktadır
        alert.setPositiveButton("onaylıyorum"){ dialog,wich->
            val intent= Intent(applicationContext,NextActivity::class.java)
            startActivity(intent)
            Toast.makeText(this," aktarılıyorsunuz ",Toast.LENGTH_LONG).show()
        }
        alert.show()

    }

}