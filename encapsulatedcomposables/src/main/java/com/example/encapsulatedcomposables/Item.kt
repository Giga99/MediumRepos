package com.example.encapsulatedcomposables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

object Item {

    @Composable
    operator fun invoke() {
        Text("Item")
    }

    @Composable
    fun Loading() {
        Text("Loading")
    }

    @Composable
    fun Error() {
        Text("Error")
    }
}
