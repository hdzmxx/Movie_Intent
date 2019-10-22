package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_baru.*

class ActivityBaru : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baru)

        val data: Bundle? = intent.extras

        judul.text = data?.get("tittle_key").toString()
        director.text = data?.get("director_key").toString()
        production.text = data?.get("production_key").toString()
        umur.text = data?.get("umur_key").toString()
        genre.text = data?.get("genre_key").toString()
        country.text = data?.get("country_key").toString()
        tanggal.text = data?.get("tanggal_key").toString()

        kembali.setOnClickListener(){
            onBackPressed()
        }
    }
}
