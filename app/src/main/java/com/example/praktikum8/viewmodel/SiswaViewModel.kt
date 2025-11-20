package com.example.praktikum8.viewmodel


import androidx.lifecycle.ViewModel
import com.example.praktikum8.model.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SiswaViewModel : ViewModel(){
    private val _statusUI = MutableStateFlow(value = Siswa())
    val  statusUI: StateFlow<Siswa> = _statusUI.asStateFlow()