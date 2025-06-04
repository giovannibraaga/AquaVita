package com.fiap.aquavita.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fiap.aquavita.utils.BR_CAPITALS
import com.google.firebase.Firebase
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.firestore

data class HelpPoint(
    val id: Long            = 0,
    val name: String          = "",
    val geolocation: GeoPoint = GeoPoint(0.0, 0.0),
    val neighborhood: String  = "",
    val city: String          = "",
    val state: String         = "",
    val zone: String          = "",
    val capacityLiters: Long   = 0
) {
    val lat get() = geolocation.latitude
    val lon get() = geolocation.longitude
}

class MapViewModel : ViewModel() {

    private val db = Firebase.firestore

    // Exponho tudo junto para a UI
    var points by mutableStateOf<List<HelpPoint>>(emptyList())
        private set

    init {
        // 1) Começa com as capitais (estáticos)
        points = BR_CAPITALS

        // 2) Acrescenta (ou substitui) pontos do Firestore
        db.collection("helpPoints")
            .addSnapshotListener { snap, _ ->
                snap?.let {
                    val dynamic = it.toObjects(HelpPoint::class.java)
                    // Evita IDs duplicados: mantém estático se não houver dinâmico com o mesmo id
                    points = (dynamic + BR_CAPITALS.filter { static ->
                        dynamic.none { it.id == static.id }
                    })
                }
            }
    }
}


