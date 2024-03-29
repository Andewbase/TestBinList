package com.example.binlistcompouse

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.binlist.main.screen.detail.DetailScreen
import com.example.binlist.main.screen.detail.DetailViewModel
import com.example.binlist.main.screen.main.MainScreen
import com.example.binlist.main.screen.main.MainViewModel
import com.example.binlist.navigation.BankCardScreen
import com.example.binlist.navigation.MainRouter
import com.example.binlist.uikit.BinListTheme
import com.example.core.Const.SEPARATOR
import com.example.core.Const.ZERO_INT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val idDetailArgument = "id"
    private val numberDetailArgument = "number"
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BinListTheme {

                val navController = rememberNavController()

                val mainViewModel = hiltViewModel<MainViewModel>()

                val mainRouter = MainRouter.Base(navController)

                NavHost(
                    navController = navController,
                    startDestination = BankCardScreen.Start.name
                ) {
                    composable(route = BankCardScreen.Start.name) {
                        HomeScreen(
                            mainViewModel,
                            mainRouter
                        )
                    }
                    dialog(route = detailRoute(), arguments = detailArgument()) {
                        Detail(
                            mainRouter
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun HomeScreen(
        mainViewModel: MainViewModel,
        mainRouter: MainRouter.Base
    ) {
        val state = mainViewModel.mainState
        val fullBankCards by mainViewModel.getFullBankCars.collectAsState(initial = emptyList())
        MainScreen(
            mainState = state,
            send = mainViewModel::send,
            listCard = fullBankCards,
            mainRouter = mainRouter
        )
    }

    @Composable
    private fun Detail(mainRouter: MainRouter.Base) {
        val detailViewModel = hiltViewModel<DetailViewModel>()

        val detailState = detailViewModel.detailState

        DetailScreen(detailState = detailState, mainRouter)
    }

    private fun detailRoute(): String{
        return  BankCardScreen.Detail.name + "?$idDetailArgument={$idDetailArgument}&$numberDetailArgument={$numberDetailArgument}"
    }

    private fun detailArgument(): List<NamedNavArgument> {
        return listOf(
            navArgument(idDetailArgument) {
                type = NavType.LongType
                defaultValue = ZERO_INT
            },
            navArgument(numberDetailArgument) {
                type = NavType.StringType
                defaultValue = SEPARATOR
            }
        )
    }


}


