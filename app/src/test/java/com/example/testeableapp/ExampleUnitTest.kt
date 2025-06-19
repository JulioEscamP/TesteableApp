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
