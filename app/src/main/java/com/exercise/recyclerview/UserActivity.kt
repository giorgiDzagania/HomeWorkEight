package com.exercise.recyclerview

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.exercise.recyclerview.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity(){
    private lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: UserViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val userToUpdate: Users? = intent.getParcelableExtra("USER",Users::class.java)

        binding.tvName.setText(userToUpdate?.name)
        binding.tvSurName.setText(userToUpdate?.surName)
        binding.tvMail.setText(userToUpdate?.email)

        binding.btnDone.setOnClickListener {
            val id = userToUpdate?.id!!.toInt()
            val name = binding.tvName.text.toString()
            val surname= binding.tvSurName.text.toString()
            val mail = binding.tvMail.text.toString()
            val updatedUser = Users(id, name, surname, mail)
            val resultIntent = Intent()
            resultIntent.putExtra("UPDATED_USER", updatedUser)
            setResult(RESULT_OK, resultIntent)
            viewModel.updateUser(updatedUser)
            finish()
        }

    }

}