package com.example.testeableapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testeableapp.ui.Screens.TipCalculatorScreen
import com.example.testeableapp.ui.theme.TesteableAppTheme
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

    // Prueba: Redondear propina y validar cambio de cálculo
    @Test
    fun roundUpTipAndValidateCalculationChange() {
        composeTestRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }

        composeTestRule.onNodeWithText("Monto de la cuenta").performTextInput("100")


        val initialTipText = getTextFromNode("Propina actual")
        val initialTip = initialTipText.substringAfter("$").toDouble()

        composeTestRule.onNodeWithText("Redondear propina").performClick()

        val newTipText = getTextFromNode("Propina actual")
        val newTip = newTipText.substringAfter("$").toDouble()

        assertTrue(
            "La propina debería ser mayor o igual que la inicial después de redondear",
            newTip >= initialTip
        )

        if (initialTip % 1.0 != 0.0) {
            assertTrue(
                "La propina debería ser un número entero después de redondear",
                newTip == kotlin.math.ceil(initialTip)
            )
        }
    }
    // Prueba: Cambiar slider del porcentaje de propina y verificar cálculo
    @Test
    fun verifyTipPercentageSliderCalculation() {
        composeTestRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }


        composeTestRule.onNodeWithText("Monto de la cuenta").performTextInput("100")

        composeTestRule.onNodeWithContentDescription("Slider porcentaje propina")
            .performTouchInput { swipeRight(startX = 0f, endX = 0.5f) }


        Thread.sleep(10000)

        composeTestRule.onRoot().printToLog("DEBUG_UI_TREE")

        composeTestRule.onRoot().printToLog("DEBUG_UI_TREE")
        composeTestRule.onNodeWithText("Porcentaje de propina: 25%").assertExists()
        composeTestRule.onNodeWithText("Propina: \$25.00").assertExists()
    }
    // Prueba: Validar presencia de elementos UI: monto, porcentaje, número de personas
    @Test
    fun validateUIElementsPresence() {
        composeTestRule.setContent {
            TesteableAppTheme {
                TipCalculatorScreen()
            }
        }

        composeTestRule.onNodeWithText("Monto de la cuenta").assertExists()
        composeTestRule.onNodeWithContentDescription("Porcentaje de propina texto").assertExists()
        composeTestRule.onNodeWithContentDescription("Número de personas texto").assertExists()
        composeTestRule.onNodeWithText("-").assertExists()
        composeTestRule.onNodeWithText("+").assertExists()
        composeTestRule.onNodeWithText("Redondear propina").assertExists()
        composeTestRule.onNodeWithContentDescription("Propina actual").assertExists()
        composeTestRule.onNodeWithContentDescription("Total por persona").assertExists()
    }

}