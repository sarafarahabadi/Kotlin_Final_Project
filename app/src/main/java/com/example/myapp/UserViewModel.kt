package com.example.myapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
    var username = mutableStateOf("")

    fun createUser( emailRegister: String, pwRegister: String) {
        Firebase.auth
            .createUserWithEmailAndPassword(emailRegister, pwRegister)
            .addOnSuccessListener {
                username.value = emailRegister
            }
    }

    fun loginUser( emailLogin: String, pwLogin: String) {
        Firebase.auth
            .signInWithEmailAndPassword(emailLogin, pwLogin)
            .addOnSuccessListener {
                username.value = emailLogin
            }
    }

    fun logoutUser() {
        Firebase.auth.signOut()
        username.value = ""
    }
}