package edu.udb.desafiopractico1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var numero1: EditText
    private lateinit var numero2: EditText
    private lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        numero1 = findViewById(R.id.numero1)
        numero2 = findViewById(R.id.numero2)
        resultado = findViewById(R.id.resultado)

        findViewById<Button>(R.id.btnSumar).setOnClickListener { operar("+") }
        findViewById<Button>(R.id.btnRestar).setOnClickListener { operar("-") }
        findViewById<Button>(R.id.btnMultiplicar).setOnClickListener { operar("*") }
        findViewById<Button>(R.id.btnDividir).setOnClickListener { operar("/") }
        findViewById<Button>(R.id.btnExponente).setOnClickListener { operar("^") }
        findViewById<Button>(R.id.btnRaiz).setOnClickListener { calcularRaiz() }
    }

    private fun obtenerNumeros(): Pair<Double, Double>? {
        val num1Str = numero1.text.toString()
        val num2Str = numero2.text.toString()

        if (num1Str.isBlank() || num2Str.isBlank()) {
            mostrarMensaje("Por favor, ingresa ambos números.")
            return null
        }

        val num1 = num1Str.toDoubleOrNull()
        val num2 = num2Str.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            mostrarMensaje("Por favor, ingresa números válidos.")
            return null
        }

        return Pair(num1, num2)
    }

    private fun operar(operacion: String) {
        val numeros = obtenerNumeros() ?: return
        val (num1, num2) = numeros

        val resultadoOperacion = when (operacion) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0.0) {
                    mostrarMensaje("No se puede dividir entre cero.")
                    return
                }
                num1 / num2
            }
            "^" -> num1.pow(num2)
            else -> {
                mostrarMensaje("Operación desconocida.")
                return
            }
        }

        resultado.text = "Resultado: $resultadoOperacion"
    }

    private fun calcularRaiz() {
        val numStr = numero1.text.toString()

        if (numStr.isBlank()) {
            mostrarMensaje("Por favor, ingresa el número para la raíz.")
            return
        }

        val num = numStr.toDoubleOrNull()
        if (num == null) {
            mostrarMensaje("Por favor, ingresa un número válido.")
            return
        }

        if (num < 0) {
            mostrarMensaje("No se puede calcular la raíz cuadrada de un número negativo.")
            return
        }

        val raiz = sqrt(num)
        resultado.text = "Resultado: $raiz"
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
