package com.example.binlistcompouse

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.binlistcompouse.screen.detail.DetailScreen
import com.example.binlistcompouse.screen.detail.DetailState
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text_field_test(){

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            DetailScreen(
                detailState = DetailState(),
                navController = navController
            )
        }

    }


}