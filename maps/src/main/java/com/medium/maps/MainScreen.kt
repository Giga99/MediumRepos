package com.medium.maps

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.medium.maps.ui.theme.MediumReposTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen() {
    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(44.810058, 20.4617586), 17f)
    }

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }

    MediumReposTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome to the MapsApp!",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            PermissionsRequired(
                multiplePermissionsState = multiplePermissionState,
                permissionsNotGrantedContent = { /* ... */ },
                permissionsNotAvailableContent = { /* ... */ }
            ) {
                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    modifier = Modifier.weight(1f),
                    properties = MapProperties(isMyLocationEnabled = true),
                    uiSettings = MapUiSettings(compassEnabled = true)
                ) {
                    GoogleMarkers()
                    Polyline(
                        points = listOf(
                            LatLng(44.811058, 20.4617586),
                            LatLng(44.811058, 20.4627586),
                            LatLng(44.810058, 20.4627586),
                            LatLng(44.809058, 20.4627586),
                            LatLng(44.809058, 20.4617586)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun GoogleMarkers() {
    Marker(
        state = rememberMarkerState(position = LatLng(44.811058, 20.4617586)),
        title = "Marker1",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
    )
    Marker(
        state = rememberMarkerState(position = LatLng(44.811058, 20.4627586)),
        title = "Marker2",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
    )
    Marker(
        state = rememberMarkerState(position = LatLng(44.810058, 20.4627586)),
        title = "Marker3",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
    )
    Marker(
        state = rememberMarkerState(position = LatLng(44.809058, 20.4627586)),
        title = "Marker4",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
    )
    Marker(
        state = rememberMarkerState(position = LatLng(44.809058, 20.4617586)),
        title = "Marker5",
        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)
    )
}
