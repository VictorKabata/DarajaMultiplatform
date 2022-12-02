package com.vickikbt.app_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vickikbt.app_android.databinding.ActivityMainBinding
import com.vickikbt.darajakmp.Daraja
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Napier.i(tag = "UI", message = "Starting android app")

        val daraja = Daraja(
            consumerKey = "NrF3UW9YCIUeTeLeamBC9HRjlaGkw6RZ",
            consumerSecret = "lARzdAdZaRAtrXZ0"
        )

        val token = runBlocking {
            daraja.requestAuthToken()
        }

        println("Auth token: $token")

        Napier.i(tag = "UI", message = "Auth token: $token")
    }
}
