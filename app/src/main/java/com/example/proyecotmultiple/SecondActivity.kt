package com.example.proyecotmultiple

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    // Crear varibles que estan en second_activity.xml
    private lateinit var edtPrecio: EditText
    private lateinit var edtTasaAnual: EditText
    private lateinit var edtTiempo: EditText
    private lateinit var chkDecimales: CheckBox
    private lateinit var tvTasaMensual: TextView
    private lateinit var tvCuotaMensual: TextView
    private lateinit var btnCalcular: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // iniciar las variables con el id de cada elemento de second_activity.xml
        edtPrecio = findViewById(R.id.edtPrecio2)
        edtTasaAnual = findViewById(R.id.edtTasaAnual)
        edtTiempo = findViewById(R.id.edtTiempo)
        chkDecimales = findViewById(R.id.chkDecimales)
        tvTasaMensual = findViewById(R.id.tvTasaMensual)
        tvCuotaMensual = findViewById(R.id.tvCuotaMensual)
        btnCalcular = findViewById(R.id.btnCalcular2)

        // convertir los valores a int y double
        val precio = edtPrecio.text.toString().toIntOrNull()
        val tasaAnual = edtTasaAnual.text.toString().toDoubleOrNull()
        val tiempo = edtTiempo.text.toString().toIntOrNull()


        //crear el evento click para el boton calcular
        btnCalcular.setOnClickListener {
            // obtener los valores de los EditText
            val precio = edtPrecio.text.toString().toIntOrNull()
            val tasaAnual = edtTasaAnual.text.toString().toDoubleOrNull()
            var tiempo = edtTiempo.text.toString().toIntOrNull()

            if (precio != null && tasaAnual != null && tiempo != null) {
                //Resultado de las funciones
                val tasa_mensual = tasaMensual_TEM(tasaAnual)
                val cuota_mensual = cuotaMensual_R(precio, tiempo, tasa_mensual)

                if (chkDecimales.isChecked) {
                    // Respuesta con decimales (2 decimales)
                    tvCuotaMensual.text = "Cuota Mensual: %.2f".format(cuota_mensual)
                    tvTasaMensual.text = "Tasa Mensual: %.4f".format(tasa_mensual, tasa_mensual * 100)
                } else {
                    // Respuesta redondeada a enteros
                    tvCuotaMensual.text = "Cuota Mensual: ${cuota_mensual.toInt()}"
                    tvTasaMensual.text = "Tasa Mensual: %.2f %%".format(tasa_mensual, tasa_mensual * 100)
                }

            }
        }

    }

    fun tasaMensual_TEM(TEA: Double): Double {
        val tasaAnualDecimal = TEA / 100
        val fraccion = 1.0 / 12.0
        val TEM = Math.pow(1 + tasaAnualDecimal, fraccion) - 1
        return TEM
    }
    fun cuotaMensual_R(precio_p: Int, tiempo_n: Int, TEM_i: Double): Double {
        val numerador = TEM_i * Math.pow(1 + TEM_i, tiempo_n.toDouble())
        val denominador = Math.pow(1 + TEM_i, tiempo_n.toDouble()) - 1
        val R = precio_p * (numerador / denominador)
        return R
    }
}