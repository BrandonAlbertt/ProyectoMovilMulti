package com.example.proyecotmultiple

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TerceraActividad : AppCompatActivity() {

    private lateinit var tvPrecioActual: TextView
    private lateinit var edtCuotaMensual: EditText
    private lateinit var edtTasaAnual: EditText
    private lateinit var edtTiempo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tercera_actividad)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // * Buscar los id de los elementos de la tercera actividad(interface)
        edtCuotaMensual = findViewById(R.id.edtCuotaMensual3)
        edtTasaAnual = findViewById(R.id.edtTasaAnual3)
        edtTiempo = findViewById(R.id.edtTiempo3)
        tvPrecioActual = findViewById(R.id.tvPrecioActual3)
        var btnCalcular = findViewById<Button>(R.id.btnCalcular3)
        var btnLimpiar = findViewById<Button>(R.id.btnLimpiar3)

        // * Limpiar los campos de texto
        btnLimpiar.setOnClickListener {
            edtCuotaMensual.text.clear()
            edtTasaAnual.text.clear()
            edtTiempo.text.clear()
            tvPrecioActual.text = ""
        }

        btnCalcular.setOnClickListener {
            val cuotaMensual = edtCuotaMensual.text.toString().toDoubleOrNull()
            val tasaAnual = edtTasaAnual.text.toString().toDoubleOrNull()
            val tiempo = edtTiempo.text.toString().toIntOrNull()

            if (cuotaMensual != null && tasaAnual != null && tiempo != null) {
                val TEM = tasaMensual_TEM(tasaAnual)
                costoProductoActual_c(cuotaMensual, TEM, tiempo)
            } else {
                tvPrecioActual.text = "Por favor ingresa todos los valores correctamente."
            }
        }



    }

    fun tasaMensual_TEM(tasaAnual_TEA: Double): Double {
        var fraccion = 1.0/12.0
        var TEM = Math.pow(1 + tasaAnual_TEA, fraccion) - 1
        return TEM
    }

    fun costoProductoActual_c(cuotaMensual: Double, tasaMensual_TEM_i: Double, tiempo_n: Int) {
        val numerador = 1 - Math.pow(1 + tasaMensual_TEM_i, -tiempo_n.toDouble())
        val costoProductoActual = cuotaMensual * (numerador / tasaMensual_TEM_i)

        tvPrecioActual.text = "Precio Actual: $costoProductoActual"
    }
}