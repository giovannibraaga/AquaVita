package com.fiap.aquavita.composable.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import org.maplibre.android.maps.MapView

@Composable
fun rememberMapLibreViewWithLifecycle(): MapView {
    val ctx       = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val mapView = remember { MapView(ctx).apply { onCreate(null) } }

    DisposableEffect(lifecycle) {
        val obs = object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner)   = mapView.onStart()
            override fun onResume(owner: LifecycleOwner)  = mapView.onResume()
            override fun onPause(owner: LifecycleOwner)   = mapView.onPause()
            override fun onStop(owner: LifecycleOwner)    = mapView.onStop()
            override fun onDestroy(owner: LifecycleOwner) = mapView.onDestroy()
        }
        lifecycle.addObserver(obs)
        onDispose { lifecycle.removeObserver(obs) }
    }
    return mapView
}