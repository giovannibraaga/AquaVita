package com.fiap.aquavita.models

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.fiap.aquavita.R
import com.fiap.aquavita.ui.theme.AquaBlue
import com.fiap.aquavita.ui.theme.Background
import com.fiap.aquavita.ui.theme.Placeholder
import com.fiap.aquavita.ui.theme.TextDefault
import com.fiap.aquavita.ui.theme.TitleNews
import com.fiap.aquavita.viewmodels.AuthViewModel
import com.fiap.aquavita.viewmodels.HomeViewModel
import com.fiap.aquavita.viewmodels.QuizViewModel
import com.fiap.aquavita.viewmodels.SignUpViewModel

class NavGraph {
    @Composable
    fun AquaVitaNav(startDestination: String = "login") {
        val nav = rememberNavController()
        NavHost(nav, startDestination = "login") {
            composable("home")  { HomeScreen(nav) }
            composable("dicas") { TipsScreen(nav) }
            composable("login") { LoginScreen(nav) }
            composable("quiz") { QuizScreen(nav) }
            composable("map")  { MapScreen() }
            composable("signup"){ SignUpScreen(nav) }
        }

    }

    private @Composable
    fun MapScreen() {
        TODO("Not yet implemented")
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeScreen(navController: NavController, vm: HomeViewModel = viewModel()) {

        val ui = vm.uiState
        val context = LocalContext.current

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("AquaVita", color = AquaBlue, fontWeight = FontWeight.Bold)
                })
            },
            bottomBar = { AquaBottomBar(navController) }
        ) { innerPadding ->

            when {
                ui.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                ui.error != null -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(ui.error, color = Color(0xFFD32F2F))
                }
                else -> LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Background)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    item {
                        Text(
                            "Notícias sobre Água",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = AquaBlue, fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                    items(ui.articles, key = { it.url ?: it.title.hashCode() }) { art ->
                        NewsCard(art) { url ->
                            url?.let {
                                val intent = Intent(Intent.ACTION_VIEW, it.toUri())
                                context.startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun NewsCard(article: Article, onClick: (String?) -> Unit) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(article.url) }
        ) {
            Column {
                article.urlToImage?.let { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                    )
                }
                Column(Modifier.padding(16.dp)) {
                    Text(
                        article.title ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = TitleNews, fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        article.description ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextDefault,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(8.dp))
                    article.publishedAt?.let {
                        val date = it.take(10)
                        Text(date, style = MaterialTheme.typography.labelSmall, color = Placeholder)
                    }
                }
            }
        }
    }

    sealed class Tab(val route: String, val icon: ImageVector, val label: String) {
        object Home  : Tab("home",  Icons.Default.Home,  "Início")
        object Educa : Tab("dicas", Icons.Default.Check, "Dicas")
        object Mapa  : Tab("map",   Icons.Default.Place, "Mapa de Ajuda")
    }

    @Composable
    fun AquaBottomBar(nav: NavController) {
        val tabs = listOf(Tab.Home, Tab.Educa, Tab.Mapa)
        val dest by nav.currentBackStackEntryAsState()
        val current = dest?.destination?.route

        NavigationBar {
            tabs.forEach { tab ->
                NavigationBarItem(
                    selected = current == tab.route,
                    onClick = { nav.navigate(tab.route) { launchSingleTop = true } },
                    icon = { Icon(tab.icon, null) },
                    label = { Text(tab.label) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AquaBlue,
                        selectedTextColor = AquaBlue
                    )
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TipsScreen(nav: NavController, vm: HomeViewModel = viewModel()) {
        val ui = vm.uiState

        val tips = listOf(
            Tip(
                title = "Reaproveitamento de Água",
                body = "A água utilizada para lavar roupas pode ser reaproveitada para limpar calçadas e pisos, economizando até 45 % de consumo mensal."
            ),
            Tip(
                title = "Banhos Conscientes",
                body = "Reduzir o tempo de banho em 5 minutos gera economia de até 50 L por pessoa. Feche a torneira ao se ensaboar."
            ),
            Tip(
                title = "Captação de Água da Chuva",
                body = "Instalar um sistema de captação de água pluvial para regar plantas pode economizar 1 000 L mensais, além de diminuir a conta."
            ),
            Tip(
                title = "Mantenha a torneira fechada ao ensaboar a louça",
                body = "a economia é de 97 litros (casa) e 223 litros (apartamento). Faça o mesmo quando desfolhar verduras e hortaliças, descascar frutas e legumes, cortar aves, carnes, peixes etc."
            ),
            Tip(
                title = "Ao lavar o carro, use um balde",
                body = "em vez de mangueira, a economia é de 176 litros."
            )
        )

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("AquaVita", color = AquaBlue, fontWeight = FontWeight.Bold)
                })
            },
            bottomBar = { AquaBottomBar(nav) }
        ) { inner ->

            when {
                ui.loading -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(inner),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

                ui.error != null -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(inner),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(ui.error, color = Color(0xFFD32F2F))
                        Spacer(Modifier.height(16.dp))
                        Button(onClick = { vm.fetchNews() }) {
                            Text("Tentar novamente")
                        }
                    }
                }

                else -> LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Background)
                        .padding(inner)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Spacer(Modifier.height(4.dp))

                        Text(
                            "Educação Hídrica",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = AquaBlue,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Aprenda como preservar nosso recurso mais precioso com estas dicas importantes.",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextDefault
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    items(tips) { tip ->
                        TipCard(tip)
                    }

                    item {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Teste seus conhecimentos sobre preservação da água.",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextDefault
                        )
                        Spacer(Modifier.height(12.dp))

                        Button(
                            onClick = { nav.navigate("quiz") },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text("Iniciar Quiz")
                        }
                        Spacer(Modifier.height(24.dp))
                    }
                    }
                }
            }
        }

    data class Tip(val title: String, val body: String)

    @Composable
    fun TipCard(tip: Tip) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    tip.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = AquaBlue,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    tip.body,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextDefault
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun QuizScreen(nav: NavController, vm: QuizViewModel = viewModel()) {

        val answers = vm.answers                      // observar recomposição
        val context = LocalContext.current

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("AquaVita", color = AquaBlue, fontWeight = FontWeight.Bold)
                })
            },
            bottomBar = { AquaBottomBar(nav) }
        ) { inner ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(inner)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                /* Cabeçalho */
                item {
                    Text(
                        "Quiz da Água",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = AquaBlue, fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Teste seus conhecimentos sobre preservação da água",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextDefault
                    )
                    Spacer(Modifier.height(8.dp))
                }

                /* Perguntas */
                itemsIndexed(vm.questions) { idx, q ->
                    QuestionCard(
                        question = q,
                        index = idx,
                        total = vm.questions.size,
                        selected = answers[idx],
                        onSelect = { vm.selectAnswer(idx, it) }
                    )
                }

                /* Botão Finalizar */
                item {
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = {
                            val correct = vm.questions.indices.count { i ->
                                vm.answers[i] == vm.questions[i].correct
                            }
                            Toast
                                .makeText(context,
                                    "Você acertou $correct de ${vm.questions.size}!",
                                    Toast.LENGTH_LONG)
                                .show()
                            nav.popBackStack()       // volta para Educação
                        },
                        enabled = vm.isQuizFinished,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                    ) {
                        Text("Finalizar Quiz")
                    }
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }

    @Composable
    fun QuestionCard(
        question: QuizQuestion,
        index: Int,
        total: Int,
        selected: Boolean?,
        onSelect: (Boolean) -> Unit
    ) {
        Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {

                /* Cabeçalho “Questão 1/5” */
                Text(
                    "Questão ${index + 1}/$total",
                    style = MaterialTheme.typography.labelSmall,
                    color = AquaBlue
                )
                Spacer(Modifier.height(4.dp))

                /* Enunciado */
                Text(
                    question.statement,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                    color = TextDefault
                )
                Spacer(Modifier.height(12.dp))

                /* Opções se ainda não respondeu */
                if (selected == null) {
                    OptionRow("Verdadeiro") { onSelect(true) }
                    Spacer(Modifier.height(8.dp))
                    OptionRow("Falso")      { onSelect(false) }
                } else {
                    /* Mostrar feedback */
                    val isCorrect = selected == question.correct
                    val bg = if (isCorrect) Color(0xFFCCF4E5) else AquaBlue.copy(alpha = 0.2f)
                    val txt = if (isCorrect) Color(0xFF065F46) else Color(0xFF003D52)

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(bg, RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = if (isCorrect) "Resposta: Correta" else "Resposta: Falso",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = txt
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(question.explanation, style = MaterialTheme.typography.bodySmall, color = TextDefault)
                }
            }
        }
    }

    @Composable
    private fun OptionRow(text: String, onClick: () -> Unit) {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .background(Color(0xFFF8FAFC), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = false, onClick = onClick, colors = RadioButtonDefaults.colors(
                unselectedColor = Placeholder, selectedColor = AquaBlue
            ))
            Spacer(Modifier.width(6.dp))
            Text(text, color = TextDefault)
        }
    }


}