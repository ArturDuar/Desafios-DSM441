package edu.udb.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class DescuentosActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etSalarioBase: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvRenta: TextView
    private lateinit var tvAFP: TextView
    private lateinit var tvISSS: TextView
    private lateinit var tvSalarioNeto: TextView
    private lateinit var tvNombre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descuentos) // Assuming your layout file is named activity_main.xml

        etNombre = findViewById(R.id.etNombre)
        etSalarioBase = findViewById(R.id.etSalarioBase)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvRenta = findViewById(R.id.tvRenta)
        tvAFP = findViewById(R.id.tvAFP)
        tvISSS = findViewById(R.id.tvISSS)
        tvSalarioNeto = findViewById(R.id.tvSalarioNeto)
        tvNombre = findViewById(R.id.tvNombre)

        btnCalcular.setOnClickListener {
            calculateDiscounts()
        }
    }

    private fun calculateDiscounts() {
        val nombre = etNombre.text.toString().trim()
        val salarioBaseStr = etSalarioBase.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el nombre del empleado.", Toast.LENGTH_SHORT).show()
            return
        }

        if (salarioBaseStr.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el salario base.", Toast.LENGTH_SHORT).show()
            return
        }

        val salarioBase = salarioBaseStr.toDoubleOrNull()
        if (salarioBase == null || salarioBase <= 0) {
            Toast.makeText(this, "El salario base debe ser un número positivo.", Toast.LENGTH_SHORT).show()
            return
        }


        val afp = salarioBase * 0.0725

        val isss = salarioBase * 0.03

        val renta = calculateRenta(salarioBase)

       val salarioNeto = salarioBase - afp - isss - renta

        val decimalFormat = DecimalFormat("#,##0.00")

        tvNombre.text = "Nombre: $nombre"
        tvRenta.text = "Renta: \$${decimalFormat.format(renta)}"
        tvAFP.text = "AFP (7.25%): \$${decimalFormat.format(afp)}"
        tvISSS.text = "ISSS (3%): \$${decimalFormat.format(isss)}"
        tvSalarioNeto.text = "Salario Neto: \$${decimalFormat.format(salarioNeto)}"

        Toast.makeText(this, "Cálculos para $nombre completados.", Toast.LENGTH_SHORT).show()
    }


    private fun calculateRenta(salario: Double): Double {
        return when {
            salario <= 472.00 -> {
                0.0
            }
            salario <= 895.24 -> {
                val exceso = salario - 472.00
                (exceso * 0.10) + 17.67
            }
            salario <= 2038.10 -> {
                val exceso = salario - 895.24
                (exceso * 0.20) + 60.00
            }
            else -> {
                val exceso = salario - 2038.10
                (exceso * 0.30) + 288.57
            }
        }
    }
}