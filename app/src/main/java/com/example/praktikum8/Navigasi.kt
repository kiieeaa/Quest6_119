package com.example.praktikum8


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktikum8.model.DataJK.JenisK
import com.example.praktikum8.view.FormSiswa
import com.example.praktikum8.view.TampilSiswa
import com.example.praktikum8.viewmodel.SiswaViewModel


enum class Navigasi {
    Formulir,
    Detail
}

@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    viewModel: SiswaViewModel = viewModel(),
    modifier: Modifier
){
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,
            modifier = Modifier.padding(isiRuang)
        ){
            composable(route = Navigasi.Formulir.name) {
                val konteks = LocalContext.current
                FormSiswa(
                    pilihanJK = JenisK.map {id -> konteks.resources.getString(id)},
                    onSubmitButtonClicked = {
                        viewModel.setSiswa(it)
                        navController.navigate(Navigasi.Detail.name)

                    }
                )
            }
            composable(route = Navigasi.Detail.name) {
                TampilSiswa(
                    statusUiSiswa = uiState.value,
                    onBackButtonClicked = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }

        }

    }
}
private fun cancelAndBackToFormulir(
    navController: NavHostController
){
    navController.popBackStack(Navigasi.Formulir.name, inclusive = false)
}
