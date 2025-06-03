package com.fiap.aquavita.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import javax.inject.Inject


class SignUpViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun onNameChange(v: String)            { uiState = uiState.copy(name = v) }
    fun onEmailChange(v: String)           { uiState = uiState.copy(email = v) }
    fun onPassChange(v: String)            { uiState = uiState.copy(pass = v) }
    fun onConfirmPassChange(v: String)     { uiState = uiState.copy(confirm = v) }

    fun signUp(onSuccess: () -> Unit) {
        if (uiState.pass != uiState.confirm) {
            uiState = uiState.copy(error = "As senhas não coincidem")
            return
        }
        uiState = uiState.copy(loading = true, error = null)

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(uiState.email.trim(), uiState.pass)
            .addOnSuccessListener { result ->
                val uid = result.user!!.uid
                val data = mapOf(
                    "name" to uiState.name,
                    "email" to uiState.email,
                    "createdAt" to FieldValue.serverTimestamp()
                )
                Firebase.firestore.collection("users").document(uid).set(data)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e ->
                        uiState = uiState.copy(loading = false, error = e.localizedMessage)
                    }
            }
            .addOnFailureListener { e ->
                uiState = uiState.copy(loading = false, error = e.localizedMessage)
            }
    }
}

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val pass: String = "",
    val confirm: String = "",
    val loading: Boolean = false,
    val error: String? = null
)
