package com.example.registrocurso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.registrocurso.ui.theme.RegistroCursoTheme

data class Curso(val nombre: String, val duracion: Int, val calificacion: Double)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroCursoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    RegistroCursoApp()
                }
            }
        }
    }
}

@Composable
fun RegistroCursoApp() {
    var nombre by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var calificacion by remember { mutableStateOf("") }

    val listaCursos = remember { mutableStateListOf<Curso>() }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Text("Registrar Curso", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del curso") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text("Duración (horas)") },

            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = calificacion,
            onValueChange = { calificacion = it },
            label = { Text("Calificación promedio") },

            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val duracionInt = duracion.toIntOrNull()
                val calificacionDouble = calificacion.toDoubleOrNull()

                if (nombre.isNotBlank() && duracionInt != null && calificacionDouble != null) {
                    listaCursos.add(Curso(nombre, duracionInt, calificacionDouble))
                    nombre = ""
                    duracion = ""
                    calificacion = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Agregar Curso")
        }

        Divider()

        Text("Lista de Cursos", style = MaterialTheme.typography.h6, modifier = Modifier.padding(top = 8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(listaCursos) { curso ->
                CursoCard(curso)
            }
        }
    }
}

@Composable
fun CursoCard(curso: Curso) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("📘 ${curso.nombre}", style = MaterialTheme.typography.h6)
            Text("⏱ Duración: ${curso.duracion} horas")
            Text("⭐ Calificación: ${curso.calificacion}")
        }
    }
}
