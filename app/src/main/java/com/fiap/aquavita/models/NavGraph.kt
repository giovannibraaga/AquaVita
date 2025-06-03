package com.fiap.aquavita.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.aquavita.R
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.Background
import com.fiap.aquavita.ui.theme.Placeholder
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.viewmodels.AuthViewModel
import com.fiap.aquavita.viewmodels.SignUpViewModel

class NavGraph {
    @Composable
    fun AquaVitaNav(startDestination: String = "login") {
        val nav = rememberNavController()
        NavHost(nav, startDestination = "login") {
            composable("home")  { HomeScreen(nav) }
            composable("login") { LoginScreen(nav) }
            composable("signup"){ SignUpScreen(nav) }
        }

    }

    private @Composable
    fun HomeScreen(nav: NavController, vm: AuthViewModel = viewModel()) {
        Text("Home Screen")
    }


    @Composable
    fun LoginScreen(nav: NavController, vm: AuthViewModel = viewModel()) {

        val ui = vm.uiState
        val scroll = rememberScrollState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // ---------- Logo + Título ----------
                Image(
                    painterResource(R.drawable.baked_goods_3),   // seu vetor/svg
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(Modifier.height(32.dp))

                Text(
                    "Preservando o futuro, gota a gota",
                    style = MaterialTheme.typography.titleMedium,
                    color = AquaBlue,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(32.dp))

                Text(
                    "E-mail",
                    style = MaterialTheme.typography.titleMedium,
                    color = AquaBlue,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = ui.email,
                    onValueChange = vm::onEmailChange,
                    label = { Text("email@email.com") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextDefault,
                        unfocusedTextColor = TextDefault,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = AquaBlue,
                        unfocusedBorderColor = Placeholder,
                        cursorColor = AquaBlue
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    "Senha",
                    style = MaterialTheme.typography.titleMedium,
                    color = AquaBlue,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = ui.password,
                    onValueChange = vm::onPasswordChange,
                    label = { Text("Sua Senha Aqui") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = TextDefault,
                        unfocusedTextColor = TextDefault,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = AquaBlue,
                        unfocusedBorderColor = Placeholder,
                        cursorColor = AquaBlue
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    shape = RoundedCornerShape(8.dp),     // canto menos arredondado
                    onClick = {
                        vm.login {
                            nav.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    },
                    enabled = ui.email.isNotBlank() && ui.password.isNotBlank() && !ui.loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    if (ui.loading) CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(22.dp),
                        strokeWidth = 2.dp
                    )
                    else Text("Entrar")
                }

                // ---------- Erro ----------
                ui.error?.let {
                    Spacer(Modifier.height(12.dp))
                    Text(it, color = Color(0xFFD32F2F))
                }

                Spacer(Modifier.height(24.dp))

                // ---------- Link para cadastro ----------
                Row {
                    Text("Não tem uma conta? ", color = TextDefault)
                    Text(
                        "Cadastre-se",
                        color = AquaBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { nav.navigate("signup") }
                    )
                }
            }
        }
    }

    @Composable
    fun SignUpScreen(nav: NavController, vm: SignUpViewModel = viewModel()) {

        val ui = vm.uiState
        val scroll = rememberScrollState()

        Surface(Modifier.fillMaxSize(), color = Background) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scroll)
                    .padding(horizontal = 32.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painterResource(R.drawable.baked_goods_1),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = ui.name,
                    onValueChange = vm::onNameChange,
                    label = { Text("Nome completo") },
                    singleLine = true,
                    colors = textFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = ui.email,
                    onValueChange = vm::onEmailChange,
                    label = { Text("E-mail") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = textFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = ui.pass,
                    onValueChange = vm::onPassChange,
                    label = { Text("Senha") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = textFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = ui.confirm,
                    onValueChange = vm::onConfirmPassChange,
                    label = { Text("Confirmar senha") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = textFieldColors(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        vm.signUp {
                            nav.navigate("home") { popUpTo("signup") { inclusive = true } }
                        }
                    },
                    enabled = !ui.loading &&
                            ui.name.isNotBlank() &&
                            ui.email.isNotBlank() &&
                            ui.pass.isNotBlank() &&
                            ui.confirm.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    if (ui.loading)
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(22.dp), strokeWidth = 2.dp)
                    else
                        Text("Cadastre-se")
                }

                ui.error?.let {
                    Spacer(Modifier.height(12.dp))
                    Text(it, color = Color(0xFFD32F2F))
                }

                Spacer(Modifier.height(24.dp))

                Row {
                    Text("Já tem uma conta? ", color = TextDefault)
                    Text(
                        "Faça login",
                        color = AquaBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { nav.navigate("login") }
                    )
                }
            }
        }
    }

    @Composable
    private fun textFieldColors() = OutlinedTextFieldDefaults.colors(
        focusedTextColor = TextDefault,
        unfocusedTextColor = TextDefault,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedBorderColor = AquaBlue,
        unfocusedBorderColor = Placeholder,
        cursorColor = AquaBlue
    )
}
