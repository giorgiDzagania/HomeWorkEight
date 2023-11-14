package com.exercise.recyclerview

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.exercise.recyclerview.databinding.ActivityAddNewUserBinding

class ActivityAddNewUser : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()

    }

    private fun setUp() {
        binding.btnDone.setOnClickListener {
            val name = binding.tvName.text.toString()
            val surname = binding.tvSurName.text.toString()
            val mail = binding.tvMail.text.toString()

            val intent = Intent()
            intent.putExtra("NEW_USER_NAME", name)
            intent.putExtra("NEW_USER_SURNAME", surname)
            intent.putExtra("NEW_USER_MAIL", mail)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}