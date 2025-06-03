package com.fiap.aquavita.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(v: String)      { uiState = uiState.copy(email = v) }
    fun onPasswordChange(v: String)   { uiState = uiState.copy(password = v) }

    fun login(onSuccess: () -> Unit) {
        uiState = uiState.copy(loading = true, error = null)
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(uiState.email.trim(), uiState.password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e ->
                uiState = uiState.copy(
                    loading = false,
                    error = e.localizedMessage ?: "Erro desconhecido"
                )
            }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: String? = null
)
