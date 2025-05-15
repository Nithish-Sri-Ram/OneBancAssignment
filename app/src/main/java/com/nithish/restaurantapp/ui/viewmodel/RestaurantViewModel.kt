package com.nithish.restaurantapp.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nithish.restaurantapp.data.model.Cart
import com.nithish.restaurantapp.data.model.Cuisine
import com.nithish.restaurantapp.data.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {
    private val repository = RestaurantRepository()

    private val _cuisines = MutableStateFlow<List<Cuisine>>(emptyList())
    val cuisines: StateFlow<List<Cuisine>> = _cuisines.asStateFlow()

    private val _cart = MutableStateFlow(Cart())
    val cart: StateFlow<Cart> = _cart.asStateFlow()

    private val _selectedCuisine = MutableStateFlow<Cuisine?>(null)
    val selectedCuisine: StateFlow<Cuisine?> = _selectedCuisine.asStateFlow()

    var isEnglish by mutableStateOf(true)
        private set

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchCuisines()
    }

    fun fetchCuisines() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _cuisines.value = repository.getCuisines()
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load cuisines: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }


}