package com.exercise.recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnItemClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter

    private lateinit var viewModel: UserViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        prepareRecyclerView()
        addNewUserBtn()

    }

    private fun prepareRecyclerView() {
        viewModel.getUsers()
        itemAdapter = ItemAdapter(viewModel.getUsers(), this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = itemAdapter
        }
    }

    override fun onDelete(user: Users) {
        viewModel.getUsers().remove(user)
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
            for (items in viewModel.getUsers()){
                if (items.id == updatedUser?.id){
                    var index = viewModel.getUsers().indexOf(items)
                    viewModel.getUsers().set(index, updatedUser)
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
            val newId = viewModel.getUsers().size
            val newName = data?.getStringExtra("NEW_USER_NAME")
            val newSurname = data?.getStringExtra("NEW_USER_SURNAME")
            val newMail = data?.getStringExtra("NEW_USER_MAIL")
            val newUser = Users(newId,newName,newSurname,newMail)
            viewModel.getUsers().add(newId,newUser)
            itemAdapter.notifyItemChanged(newId)
        }
    }


}