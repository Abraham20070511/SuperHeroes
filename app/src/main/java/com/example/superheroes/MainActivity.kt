/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.model.HeroesRepository
import com.example.superheroes.ui.theme.SuperheroesTheme

// Clase principal de la aplicación, representa la Activity de entrada.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita diseño de borde a borde (sin padding por defecto en status/nav bar)
        setContent {
            // Aplica el tema personalizado SuperheroesTheme a toda la interfaz
            SuperheroesTheme {
                // Surface proporciona un fondo utilizando el color del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperheroesApp() // Llama al composable principal de la app
                }
            }
        }
    }

    //  Esta función composable está definida dentro de la Activity, lo cual NO es recomendable.
    //  Debería definirse fuera de la clase MainActivity para seguir buenas prácticas de Jetpack Compose.

    /**
     * Composable principal que muestra la estructura general de la app: una TopAppBar y una lista de héroes.
     */
    @Composable
    fun SuperheroesApp() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar() // Barra superior centrada con el nombre de la app
            }
        ) {
            // Acceder directamente al repositorio desde la UI no es una buena práctica.
            // En el futuro se usará un ViewModel que exponga los datos.
            val heroes = HeroesRepository.heroes
            HeroesList(heroes = heroes, contentPadding = it) // Muestra la lista de héroes
        }
    }

    /**
     * Composable que muestra una barra superior con el nombre de la app.
     *
     * @param modifier permite modificar el comportamiento visual del componente
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(modifier: Modifier = Modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name), // Obtiene el texto desde strings.xml
                    style = MaterialTheme.typography.displayLarge,
                )
            },
            modifier = modifier
        )
    }

    /**
     * Vista previa para Android Studio: permite ver cómo se verá la interfaz sin ejecutar la app.
     */
    @Preview(showBackground = true)
    @Composable
    fun SuperHeroesPreview() {
        SuperheroesTheme {
            SuperheroesApp()
        }
    }
}
