package com.example.testeableapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import org.junit.Rule
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMontoNoNumericoMuestraCero() {
        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        composeTestRule
            .onNodeWithText("Monto de la cuenta")
            .performTextInput("abc")

        composeTestRule
            .onNodeWithText("Propina: $0.00")
            .assertExists()

        composeTestRule
            .onNodeWithText("Total por persona: $0.00")
            .assertExists()
    }

    @Test
    fun testBotonesDePersonasIncrementanYDecrementanCorrectamente() {
        composeTestRule.setContent {
            TipCalculatorScreen()
        }

        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("+").performClick()

        composeTestRule.onNodeWithText("-").performClick()

        composeTestRule.onNodeWithText("2").assertExists()

        composeTestRule.onNodeWithText("-").performClick()
        composeTestRule.onNodeWithText("-").performClick()

        composeTestRule.onNodeWithText("1").assertExists()
    }
}