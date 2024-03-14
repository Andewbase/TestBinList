package com.example.binlist.main

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.binlist.main.screen.detail.DetailScreen
import com.example.binlist.main.screen.detail.DetailState
import com.example.core.Const.TEXT_CITY_TEST
import com.example.core.Const.TEXT_PHONE2_TEST
import com.example.core.Const.TEXT_PHONE_TEST
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text_city_test(){

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            DetailScreen(
                detailState = DetailState(),
                navController = navController
            )
        }

        composeTestRule.onNodeWithTag(TEXT_CITY_TEST).performClick()
    }

    @Test
    fun text_phone_test(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            DetailScreen(
                detailState = DetailState(),
                navController = navController
            )
        }

        composeTestRule.onNodeWithTag(TEXT_PHONE_TEST).performClick()
    }

    @Test
    fun text_phone2_test(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            DetailScreen(
                detailState = DetailState(),
                navController = navController
            )
        }

        composeTestRule.onNodeWithTag(TEXT_PHONE2_TEST).performClick()
    }


}