package edu.udb.desafiopractico1

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PromedioActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_promedio)
        val nota1 = findViewById<EditText>(R.id.nota1)
        val nota2 = findViewById<EditText>(R.id.nota2)
        val nota3 = findViewById<EditText>(R.id.nota3)
        val nota4 = findViewById<EditText>(R.id.nota4)
        val nota5 = findViewById<EditText>(R.id.nota5)
        val calcular = findViewById<Button>(R.id.calcular)
        val resultado = findViewById<TextView>(R.id.resultado)



        calcular.setOnClickListener {

            if (nota1.text.toString().isEmpty() || nota2.text.toString().isEmpty() || nota3.text.toString().isEmpty() || nota4.text.toString().isEmpty() || nota5.text.toString().isEmpty()) {
                Toast.makeText(this, "Por favor ingrese todas las notas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nota1Valor = nota1.text.toString().toDouble()
            val nota2Valor = nota2.text.toString().toDouble()
            val nota3Valor = nota3.text.toString().toDouble()
            val nota4Valor = nota4.text.toString().toDouble()
            val nota5Valor = nota5.text.toString().toDouble()

            val notas = listOf(nota1Valor, nota2Valor, nota3Valor, nota4Valor, nota5Valor)
            if (notas.any { it !in 0.0..10.0 }) {
                Toast.makeText(this, "Todas las notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val promedio = calcularPromedio(nota1Valor, nota2Valor, nota3Valor, nota4Valor, nota5Valor)
            val estado = calcularEstado(promedio)
            resultado.text = "El promedio es: $promedio y su estado es: $estado"
        }

    }

    fun calcularPromedio(nota1: Double, nota2: Double, nota3: Double, nota4: Double, nota5: Double): Double {
        val prom = (nota1 + nota2 + nota3 + nota4 + nota5) / 5
        return DecimalFormat("#.##").format(prom).toDouble()
    }

    fun calcularEstado(promedio: Double): String {
        return when {
            promedio in 7.0..10.0 -> "Aprobado"
            else -> "Reprobado"
        }
    }

}