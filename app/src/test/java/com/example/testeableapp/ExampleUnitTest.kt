package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Test

import org.junit.Assert.*


class ExampleUnitTest {
    @Test
    fun calculateTip_20Percent_NoRoundUp() {
        val amount = 100.0
        val tipPercent = 20
        val roundUp = false
        val expectedTip = 20.0
        val actualTip = calculateTip(amount, tipPercent, roundUp)
        assertEquals(expectedTip, actualTip, 0.001)
    }
}