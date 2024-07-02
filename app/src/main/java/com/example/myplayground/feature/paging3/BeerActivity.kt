package com.example.myplayground.feature.paging3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.myplayground.ui.theme.MyPlayGroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPlayGroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<BeerViewModel>()
                    val beers = viewModel.beerPagingFlow.collectAsLazyPagingItems()
                    BeerScreen(beers = beers)
                }
            }
        }
    }
}