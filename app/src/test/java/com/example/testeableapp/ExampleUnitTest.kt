package com.example.testeableapp

import com.example.testeableapp.ui.Screens.calculateTip
import org.junit.Test
import org.junit.Assert.assertEquals
import com.example.testeableapp.ui.components.calcularPropina
import com.example.testeableapp.ui.components.ResultadoPropina


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

    @Test
    fun calculateTip_15Percent_RoundUp() {
        val amount = 97.0
        val tipPercent = 15
        val roundUp = true
        val expectedTip = 15.0
        val actualTip = calculateTip(amount, tipPercent, roundUp)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    fun calculateTip_NegativeAmount() {
        val amount = -50.0
        val tipPercent = 15
        val roundUp = false
        val expectedTip = 0.0
        val actualTip = calculateTip(amount, tipPercent, roundUp)
        assertEquals(expectedTip, actualTip, 0.001)
    }

    @Test
    fun calculateTotalPerPerson_WithTip() {
        val billAmount = 100.0
        val tipPercent = 15
        val roundUp = false
        val numberOfPeople = 4

        val tip = calculateTip(billAmount, tipPercent, roundUp)

        val expectedTotalPerPerson = 28.75

        val totalPerPerson = if (numberOfPeople > 0) (billAmount + tip) / numberOfPeople else 0.0

        assertEquals(expectedTotalPerPerson, totalPerPerson, 0.001)
    }


    @Test
    fun calcularPropina_conMontoDecimalYPorcentajeFijo() {
        val monto = 123.45
        val porcentaje = 15
        val propinaEsperada = monto * porcentaje / 100
        val totalEsperado = monto + propinaEsperada

        val resultado = calcularPropina(monto, porcentaje, redondear = false)

        assertEquals(propinaEsperada, resultado.propina, 0.01)
        assertEquals(totalEsperado, resultado.total, 0.01)
    }

    @Test
    fun calcularTotalPorPersona_conCeroPersonas_devuelveCero() {
        val monto = 100.0
        val porcentaje = 10
        val personas = 0

        val resultado = calcularPropina(monto, porcentaje, redondear = false)

        val totalPorPersona = if (personas > 0) resultado.total / personas else 0.0

        assertEquals(0.0, totalPorPersona, 0.01)
    }
}
