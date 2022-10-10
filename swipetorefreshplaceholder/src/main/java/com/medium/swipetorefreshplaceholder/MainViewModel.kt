package com.medium.swipetorefreshplaceholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _currentTime = MutableStateFlow(Instant.now())
    val currentTime = _currentTime.asStateFlow()

    private val _items = MutableStateFlow(List(size = 20) { RowItem() })
    val items = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _items.value = generateItems()
            _isLoading.value = false
        }
    }

    fun refresh() = viewModelScope.launch {
        _isRefreshing.update { true }
        // Simulate API call
        delay(2000)
        _currentTime.value = Instant.now()
        _items.value = generateItems()
        _isRefreshing.update { false }
    }

    private fun generateItems(): List<RowItem> {
        val list = mutableListOf<RowItem>()

        for (i in 1 until 20) {
            list.add(
                RowItem(
                    rowImage = randomSampleImageUrl(),
                    number = Random.nextInt(1, 1000)
                )
            )
        }

        return list
    }

    private fun randomSampleImageUrl(
        seed: Int = (0..100000).random(),
        width: Int = 300,
        height: Int = width,
    ): String {
        return "https://picsum.photos/seed/$seed/$width/$height"
    }
}

data class RowItem(
    val rowImage: String = "",
    val number: Int = -1
)
