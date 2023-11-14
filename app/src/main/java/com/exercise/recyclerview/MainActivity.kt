package com.exercise.recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnItemClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private var users = getUser()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        addNewUserBtn()
    }

    private fun prepareRecyclerView() {
        itemAdapter = ItemAdapter(users, this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = itemAdapter
        }
    }

    private fun getUser(): MutableList<Users> {
        val users = mutableListOf<Users>()

        val note1 = Users(0, "giorgi", "dzagania", "georgeDzagania@gmail.com")
        val note2 = Users(1, "nika", "dzagania", "nikoloozi@gmail.com")
        val note3 = Users(2, "giorgi", "mikautadze", "ASdasd@gmail.ru")
        val note4 = Users(3, "learn", "coding", "asmdaksdk@gmail.com")
        val note5 = Users(4, "Brad", "pitt", "bradPitt@gmail.com")
        val note6 = Users(5, "harvy", "Keen", "garhby@gmail.com")
        val note7 = Users(6, "Diton", "cincadze", "cincadze1993@gmail.com")
        val note8 = Users(7, "Ilon", "Mask", "ilonMaskSpacex@gmail.com")
        val note9 = Users(8, "Ana", "chakvetadze", "anaChakve@gmail.com")
        val note10 = Users(9, "Tako", "yipiani", "takoYipiani1991@gmail.com")

        users.add(note1)
        users.add(note2)
        users.add(note3)
        users.add(note4)
        users.add(note5)
        users.add(note6)
        users.add(note7)
        users.add(note8)
        users.add(note9)
        users.add(note10)
        return users
    }

    override fun onDelete(user: Users) {
        users.remove(user)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onUpdate(user: Users) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("USER", user)
        resultLauncher.launch(intent)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d("resultLauncher","resultLauncher")
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val updatedUser = data?.getParcelableExtra("UPDATED_USER",Users::class.java)

            Log.d("updatedUser", updatedUser.toString())
            for (items in users){
                if (items.id == updatedUser?.id){
                    var index = users.indexOf(items)
                    users.set(index, updatedUser)
                    itemAdapter.notifyItemChanged(index)
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun addNewUserBtn(){
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, ActivityAddNewUser::class.java)
            resultNewUserLauncher.launch(intent)
        }
    }

    private var resultNewUserLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if (result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            val newId = users.size
            val newName = data?.getStringExtra("NEW_USER_NAME")
            val newSurname = data?.getStringExtra("NEW_USER_SURNAME")
            val newMail = data?.getStringExtra("NEW_USER_MAIL")
            val newUser = Users(newId,newName,newSurname,newMail)
            users.add(newId,newUser)
            itemAdapter.notifyItemChanged(newId)
        }
    }

}