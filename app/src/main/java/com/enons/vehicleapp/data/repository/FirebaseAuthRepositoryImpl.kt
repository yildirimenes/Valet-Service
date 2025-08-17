package com.enons.vehicleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.presentation.screens.authPage.LoginPage.AuthState
import com.enons.vehicleapp.data.remote.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Instant
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : FirebaseAuthRepository {

    private val _authState = MutableLiveData<AuthState>()
    override val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        _authState.value = if (auth.currentUser == null) {
            AuthState.Unauthenticated
        } else {
            AuthState.Authenticated
        }
    }

    override fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _authState.value = if (task.isSuccessful) {
                    AuthState.Authenticated
                } else {
                    AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    override fun registerUser(
        email: String,
        password: String,
        companyName: String,
        valetName: String
    ) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: return@addOnCompleteListener
                    val user = User(
                        company_name = companyName,
                        valet_name_surname = valetName,
                        email = email,
                        createdAt = Instant.now().toString()
                    )
                    firestore.collection("users").document(uid).set(user)
                        .addOnSuccessListener { _authState.value = AuthState.Authenticated }
                        .addOnFailureListener { e ->
                            _authState.value = AuthState.Error(e.message ?: "Something went wrong")
                        }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    override fun sendPasswordReset(email: String, callback: (Boolean, String?) -> Unit) {
        if (email.isEmpty()) {
            callback(false, "Email can't be empty")
            return
        }
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message ?: "Something went wrong")
                }
            }
    }

    override fun getUserData(callback: (User?) -> Unit) {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            callback(null)
            return
        }
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                callback(user)
            }
            .addOnFailureListener { callback(null) }
    }

    override fun signout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}