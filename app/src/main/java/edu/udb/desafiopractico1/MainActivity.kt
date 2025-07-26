package edu.udb.desafiopractico1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val promedio = findViewById<Button>(R.id.promedio)
        val descuentos = findViewById<Button>(R.id.descuentos)
        val calculadora = findViewById<Button>(R.id.calculadora)

        promedio.setOnClickListener {
            val intent = Intent(this, PromedioActivity::class.java)
            startActivity(intent)
        }

        descuentos.setOnClickListener {
            val intent = Intent(this, DescuentosActivity::class.java)
            startActivity(intent)
        }

        calculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }



    }
}