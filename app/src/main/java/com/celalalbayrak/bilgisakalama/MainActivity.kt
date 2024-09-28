package com.celalalbayrak.bilgisakalama

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.celalalbayrak.bilgisakalama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var sharedPreferences: SharedPreferences
    var alinanKullaniciAdi :String? = null // boş bir string atıyoruz....

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()
               ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = this.getSharedPreferences("com.celalalbayrak.bilgisakalama", Context.MODE_PRIVATE)// projeinin adıyla saklanmasını sağlıyoruz....

        alinanKullaniciAdi = sharedPreferences.getString("isim", "")
        if (alinanKullaniciAdi != null) {
            binding.textView.text = "kullaniciAdi : "+alinanKullaniciAdi
        }else{
            binding.textView.text = "kullaniciAdi : "
        }

    }


    fun kaydet(view: View) {
        val kullaniciAdi = binding.editText.text.toString()

        if(kullaniciAdi.isEmpty()) {
            Toast.makeText(this@MainActivity, "Kullanici Adi Empty", Toast.LENGTH_LONG).show()
        }else{
            sharedPreferences.edit().putString("isim", kullaniciAdi).apply()
            binding.textView.text = kullaniciAdi
        }


    }

    fun sil(view: View){
        alinanKullaniciAdi = sharedPreferences.getString("isim", "")
        if (alinanKullaniciAdi != null) { // boş degilse işlem yapılsın.
            sharedPreferences.edit().remove("isim").apply()

        }

        binding.textView.text = "kullaniciAdi : "

    }

}