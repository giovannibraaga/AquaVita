package com.fiap.aquavita.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.firestore

data class City(
    val id: String = "",
    val name: String = "",
    val geolocation: GeoPoint = GeoPoint(0.0, 0.0)
) {
    val lat get() = geolocation.latitude
    val lon get() = geolocation.longitude
}

data class HelpPoint(
    val cityId: String = "",
    val name: String = "",
    val geolocation: GeoPoint = GeoPoint(0.0, 0.0),
    val capacityLiters: Long = 0
) {
    val lat get() = geolocation.latitude
    val lon get() = geolocation.longitude
}


class MapViewModel : ViewModel() {

    private val db = Firebase.firestore

    var cities by mutableStateOf<List<City>>(emptyList())
        private set

    var helpPoints by mutableStateOf<List<HelpPoint>>(emptyList())
        private set

    init {
        db.collection("cities")
            .addSnapshotListener { snap, _ ->
                snap?.let {
                    cities = it.documents.mapNotNull { doc ->
                        doc.toObject(City::class.java)
                            ?.copy(id = doc.id)
                    }
                }
            }

        db.collection("helpPoints")
            .addSnapshotListener { snap, _ ->
                snap?.let { helpPoints = it.toObjects(HelpPoint::class.java) }
            }
    }

    fun pointsFor(cityId: String) = helpPoints.filter { it.cityId == cityId }
}



