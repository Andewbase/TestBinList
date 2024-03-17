package com.example.binlist.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

interface MainRouter {

    fun launchDetailScreen(numberCard: String)

    fun launchDetailScreen(id: Long)

    fun popBackStack()

    @Singleton
    class Base @Inject constructor(private val navController: NavController): MainRouter{
        override fun launchDetailScreen(numberCard: String) {
            navController.navigate(route = BankCardScreen.Detail.name + "?number=${numberCard}")
        }

        override fun launchDetailScreen(id: Long) {
            navController.navigate(route = BankCardScreen.Detail.name + "?id=${id}")
        }

        override fun popBackStack() {
            navController.popBackStack()
        }
    }
}