package com.exercise.recyclerview

import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private var users: MutableList<Users> = mutableListOf()

    fun getUsers(): MutableList<Users> {
        if (users.isEmpty()) {
            users = getUser()
        }
        return users
    }

    fun updateUser(updatedUser: Users) {
        for (item in users) {
            if (item.id == updatedUser.id) {
                val index = users.indexOf(item)
                users[index] = updatedUser
                break
            }
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

}