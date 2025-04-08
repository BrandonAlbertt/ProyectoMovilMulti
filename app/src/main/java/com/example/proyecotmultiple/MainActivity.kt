package com.example.proyecotmultiple

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Declarar variables para los elementos de la interfaz
    private lateinit var spnProducto: Spinner
    private lateinit var edtCantidad: EditText
    private lateinit var tvPrecio: TextView
    private lateinit var tvTotal: TextView
    private lateinit var chkDelivery: CheckBox
    private lateinit var tvDescuento: TextView
    private lateinit var tvTotalPagar: TextView
    private lateinit var btnCalcular: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ! Busca el botón en el XML por su ID y lo guarda en una variable
        val btnVentana2 = findViewById<Button>(R.id.btnVentana2)
        // ! Cuando se hace clic en el botón...
        btnVentana2.setOnClickListener {
            // ! Crea un "intento" para cambiar de pantalla (de esta actividad a SecondActivity)
            var intent = Intent(this, SecondActivity::class.java)
            // ! Inicia la nueva actividad (abre la segunda pantalla)
            startActivity(intent)
        }

        // Inicializar los elementos de la interfaz
        spnProducto = findViewById(R.id.spnProducto)
        edtCantidad = findViewById(R.id.edtCantidad)
        tvPrecio = findViewById(R.id.tvPrecio)
        tvTotal = findViewById(R.id.tvTotal)
        chkDelivery = findViewById(R.id.chkDelivery)
        tvDescuento = findViewById(R.id.tvDescuento)
        tvTotalPagar = findViewById(R.id.tvTotalPagar)
        btnCalcular = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            // Obtener el valor seleccionado del Spinner
            val categoriaSeleccionada = spnProducto.selectedItem.toString()
            // Obtener la posición de la categoría seleccionada
            val posicionSeleccionada = spnProducto.selectedItemPosition
            // Mostrar el valor y la posición en la pantalla de android
            Toast.makeText(this, "Categoría seleccionada: $categoriaSeleccionada, Posición: $posicionSeleccionada", Toast.LENGTH_SHORT).show()
            //obtener la cantidad en numero
            val cantidad =edtCantidad.text.toString().toIntOrNull()

            if (cantidad != null) {
                // Calculos a realizar
                when (posicionSeleccionada) {
                    0 -> {
                        Toast.makeText(this, "Seleccionaste la categoria: $categoriaSeleccionada, no selecionaste nada", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        calculo(cantidad, 65.50)
                    }
                    2 -> {
                        calculo(cantidad, 34.50)
                    }
                    3 -> {
                        calculo(cantidad, 18.50)
                    }
                    4 -> {
                        calculo(cantidad, 17.50)
                    }
                    5 -> {
                        calculo(cantidad, 21.50)
                    }
                }

            } else {
                Toast.makeText(this, "Cantidad erronea", Toast.LENGTH_SHORT).show()
            }


        }

    }

    fun calculo(cantidad: Int, precioProducto: Double) {
        val precioDescuento = 5
        var totalPagar = 0.0
        val total = cantidad * precioProducto
        tvDescuento.text = "Descuento: s/. $precioDescuento"
        tvTotal.text = "Total: s/. $total"
        if (chkDelivery.isChecked) {
            totalPagar = (total + 10) - precioDescuento
            tvTotalPagar.text = "Total a pagar: s/. $totalPagar"
        } else {
            totalPagar = total - precioDescuento
            tvTotalPagar.text = "Total a pagar: s/. $totalPagar"
        }
    }


}