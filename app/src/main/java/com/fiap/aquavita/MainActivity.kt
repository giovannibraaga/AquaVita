package com.fiap.aquavita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fiap.aquavita.models.NavGraph
import com.fiap.aquavita.ui.theme.AquaVitaTheme
import com.google.firebase.FirebaseApp
import org.maplibre.android.MapLibre

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            AquaVitaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background ,
                ) {
                    MapLibre.getInstance(this)
                    NavGraph().AquaVitaNav()
                }
            }
        }
    }
}