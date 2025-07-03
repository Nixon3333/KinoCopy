package com.drygin.kinocopy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.drygin.kinocopy.features.home.presentation.ui.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, MainFragment())
                .commit()
        }
    }
}