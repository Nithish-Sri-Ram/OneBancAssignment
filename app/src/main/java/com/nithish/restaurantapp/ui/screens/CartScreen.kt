package com.nithish.restaurantapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nithish.restaurantapp.ui.components.CartItemComponent
import com.nithish.restaurantapp.ui.viewmodel.RestaurantViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: RestaurantViewModel,
    onBackClick: () -> Unit,
    onOrderSuccess: () -> Unit
) {
    val cart by viewModel.cart.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var orderSuccess by remember { mutableStateOf(false) }
    var orderTxnRef by remember { mutableStateOf("") }

    LaunchedEffect(orderSuccess) {
        if (orderSuccess) {
            delay(2000)
            onOrderSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (viewModel.isEnglish) "Cart" else "कार्ट"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (cart.items.isEmpty()) {
                Text(
                    text = if (viewModel.isEnglish)
                        "Your cart is empty"
                    else
                        "आपका कार्ट खाली है",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        items(cart.items) { item ->
                            CartItemComponent(
                                item = item,
                                onIncrease = {
                                    viewModel.addToCart(
                                        cuisine = cart.items.first { it.itemId == item.itemId }.let {
                                            viewModel.cuisines.value.first { cuisine ->
                                                cuisine.cuisineId == it.cuisineId
                                            }
                                        },
                                        item = viewModel.cuisines.value
                                            .flatMap { it.items }
                                            .first { it.id == item.itemId }
                                    )
                                },
                                onDecrease = { viewModel.removeFromCart(item.itemId) }
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (viewModel.isEnglish) "Net Total" else "कुल राशि",
                                    fontSize = 16.sp
                                )

                                Text(
                                    text = "₹${String.format("%.2f", cart.getNetTotal())}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (viewModel.isEnglish) "CGST (2.5%)" else "सीजीएसटी (2.5%)",
                                    fontSize = 14.sp
                                )

                                Text(
                                    text = "₹${String.format("%.2f", cart.getNetTotal() * cart.cgst)}",
                                    fontSize = 14.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (viewModel.isEnglish) "SGST (2.5%)" else "एसजीएसटी (2.5%)",
                                    fontSize = 14.sp
                                )

                                Text(
                                    text = "₹${String.format("%.2f", cart.getNetTotal() * cart.sgst)}",
                                    fontSize = 14.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider()
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (viewModel.isEnglish) "Grand Total" else "कुल योग",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "₹${String.format("%.2f", cart.getGrandTotal())}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    viewModel.placeOrder(
                                        onSuccess = { txnRef ->
                                            orderSuccess = true
                                            orderTxnRef = txnRef

                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = if (viewModel.isEnglish)
                                                        "Order placed successfully: $txnRef"
                                                    else
                                                        "ऑर्डर सफलतापूर्वक दर्ज किया गया: $txnRef",
                                                    duration = SnackbarDuration.Indefinite
                                                )
                                            }
                                        },
                                        onError = { error ->
                                            scope.launch {
                                                snackbarHostState.showSnackbar(
                                                    message = error,
                                                    duration = SnackbarDuration.Long
                                                )
                                            }
                                        }
                                    )
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = if (viewModel.isEnglish) "Place Order" else "ऑर्डर करें",
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}