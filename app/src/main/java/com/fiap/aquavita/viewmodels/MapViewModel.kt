package com.fiap.aquavita.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
    var points by mutableStateOf(listOf<HelpPoint>())
        private set

    init {
        db.collection("helpPoints")
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    Log.e("MapVM", "Erro Firestore", err)
                    return@addSnapshotListener
                }
                snap?.let {
                    Log.d("MapVM", "Recebi ${it.size()} docs")
                    points = it.toObjects(HelpPoint::class.java)
                }
            }
    }

}

