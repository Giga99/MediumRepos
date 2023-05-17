package com.example.encapsulatedcomposables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.encapsulatedcomposables.ui.theme.MediumReposTheme

@Composable
fun MainScreen() {
    Column {
        PrimaryButton(onClick = { /*TODO*/ })
        PrimaryButton.Large(onClick = { /*TODO*/ })
        PrimaryButton.Small(onClick = { /*TODO*/ })

        val result: Result<String> = remember { Result.Success("Success") }

        when (result) {
            is Result.Success -> Item()
            is Result.Loading -> Item.Loading()
            is Result.Error -> Item.Error()
        }
    }
}

sealed class Result<out T> {
    object Loading : Result<Nothing>()

    data class Success<T>(val data: T) : Result<T>()

    data class Error(val message: String) : Result<Nothing>()
}

@Preview
@Composable
fun MainScreenPreview() {
    MediumReposTheme {
        MainScreen()
    }
}
