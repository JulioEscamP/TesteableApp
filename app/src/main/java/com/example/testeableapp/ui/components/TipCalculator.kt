package com.example.testeableapp.ui.components

data class ResultadoPropina(val propina: Double, val total: Double)

fun calcularPropina(monto: Double, porcentaje: Int, redondear: Boolean): ResultadoPropina {
    if (monto < 0) return ResultadoPropina(0.0, 0.0)

    var propina = monto * porcentaje / 100
    if (redondear) {
        propina = kotlin.math.round(propina)
    }
    val total = monto + propina
    return ResultadoPropina(propina, total)
}
