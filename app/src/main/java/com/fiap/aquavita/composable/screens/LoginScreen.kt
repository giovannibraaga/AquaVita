package com.fiap.aquavita.composable.screens

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
import com.fiap.aquavita.R
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.Background
import com.fiap.aquavita.ui.theme.Placeholder
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.viewmodels.AuthViewModel

/**
 * Tela de login que permite aos usuários acessarem o aplicativo AquaVita.
 *
 * Esta tela apresenta um formulário de autenticação com campos para e-mail e senha,
 * além de um botão para realizar o login. Durante o processo de autenticação,
 * um indicador de carregamento é exibido. Em caso de erro, uma mensagem é mostrada
 * ao usuário. Também oferece navegação para a tela de cadastro para novos usuários.

 * O estado da UI é gerenciado pelo AuthViewModel, que controla os valores dos campos,
 * estado de carregamento e mensagens de erro.
 *
 * @param nav O controlador de navegação utilizado para transitar entre telas
 * @param vm O ViewModel que gerencia o estado da tela de login, incluindo campos de entrada
 *           e processo de autenticação (instanciado automaticamente se não fornecido)
 */
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
            Image(
                painterResource(R.drawable.aquavita_logo_no_background),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
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
                placeholder = { Text("Digite seu e-mail") },
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
                placeholder = { Text("Sua Senha Aqui") },
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
                shape = RoundedCornerShape(8.dp),
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
            ui.error?.let {
                Spacer(Modifier.height(12.dp))
                Text(it, color = Color(0xFFD32F2F))
            }

            Spacer(Modifier.height(24.dp))

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