package com.example.readingtracking

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.view.View
import com.example.readingtracking.databinding.ActivityNextBinding
class NextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNextBinding
    private lateinit var  sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBinding()
        newPage()

    //life cycle metodlarını uygulamamda kullanamdım çünkü kullanılacak bir alanı olmadı
    }




    fun setupBinding() {
        binding = ActivityNextBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun newPage (){
        // bu kısımda geçen activty de aldığımız kullanıcı adını geri çağırdık ve kullandık
        sharedPreferences = this.getSharedPreferences("com.example.readingtracking", MODE_PRIVATE)
        val name=sharedPreferences.getString(Shared.UserName.get,"null")

        if(name== ""){
            binding.kullaniciAdi.text="kullanıcı adı girilmedi !! deneme sürümü olduğu için hata verilmedi"
        }else{
            binding.kullaniciAdi.text=name

        }


    }
        // hesaplama işlemleri
    fun calculator (view:View){
        val page =  binding.pageNumber.text.toString().toInt()
        val name = binding.bookName.text.toString()

        if(page>=50){
            binding.result.text=name+" isimli kitapdan "+page+" sayfa okuduğunuz için "+"Tebrik ederiz ileri dönük yatırımlarınız takdire Şayan"
        }
        else if(page>25 && page<50){
            binding.result.text=name+" isimli kitapdan "+page+" sayfa okuduğunuz için "+" mutluyuz boş zamanlarınızı gayet iyi değerlendiriyorsunuz"
        }
        else if(page<=25){
            binding.result.text=name+" isimli kitapdan "+page+" sayfa okudunuz "+" bunun faydasını göreceksiniz"
        }
        else{
            binding.result.text=" ne demek istediğiniz anlaşılamıyor"
        }
            //süre bitince ilk sayfaya aktarılacak
        object : CountDownTimer(10000,1000) {
            override fun onFinish() {
                binding.timer.text="ik sayfaya aktarılıyor..."
                val intent= Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }

            override fun onTick(p0: Long) {
                binding.timer.text="${(p0/1000)+1}"
            }
        }.start()
    }

}